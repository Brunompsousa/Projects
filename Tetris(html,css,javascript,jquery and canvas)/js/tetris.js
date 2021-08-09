//const para podermos criar os graficos
const canvas = document.getElementById('tetris');

//const para podermos desenhar
const context = canvas.getContext('2d');

//escala do campo de jogo
context.scale(20, 20);

let countLines = 1;  // contador de linhas para fazer a passagem de nivel
        let lvl= 1; // variaver que tem o nivel em que vamos


/*funcao para quando uma linha no array do campo de jogo esta todo a 1, esta seja "apagado" e tudo o jogo que esta a cima
  da linha "apagada" seja movido uma linha para baixo
  
  nesta funcao vamos verificar as linhas a contar da primeira coluna, caso esta encontre um '0' quer dizer que a linha nao esta completa, logo nao faz sentido estarmos a verificar o resto da linha.
  Assim sendo vamos fazer um salto para que voltemos a primeira coluna e comecamos a verificar entao a proxima linha.
*/
function apagaLinha(){
    let rowCounter = 1; //variavel para contar as linhas, para o calculo da pontuacao
    outer: for (let y = arena.length - 1;y > 0; --y){
        for (let x = 0;x < arena[y].length; ++x){
            if(arena[y][x] === 0){
                continue outer;
            }
        }
        const row = arena.splice(y,1)[0].fill(0); //pega na linha que esta toda a '1' e mete tudo a '0' e guarda em 'row'
        arena.unshift(row); //insere a linha no inicio o array
        ++y;
        
        /*calculo da pontuacao por cada linha, multiplas linhas dao pontuacao bonus
        * 1 = 100 2+ = numero de linhas + 100 
        */
        player.score += rowCounter * 100;
        rowCounter *= 2;
        
        // Quando e feita uma linha e esta e apagada, a velocidade vai diminuindo em 5 minisegundos aos 1000 minisegundos que temos de origem, progressivamente para que o utilizador nao note tanto
        dropInterval -=5; 
        
        countLines +=1; // quando fazemos uma linha, contamos nesta variavel
        
        if(countLines > 10)  // se fizemos mais de 10 linhas o contador volta a 0 e passamos para o proximo nivel
          {
          
              lvl +=1;
              countLines = 0;
              updatelvl();     // funcao que muda o lvl no ecran
              
          }

    }
}



//funcao para sabermos quando uma peça colide com outra ou com os limites do campo de jogo
function colisao(arena,player){
    const [m, o] = [player.matrix,player.pos];
    for(let y = 0; y < m.length; ++y){
        for(let x = 0; x < m[y].length; ++x){
            if(m[y][x] !== 0 &&
              (arena[y + o.y] &&
              arena[y + o.y][x + o.x]) !== 0){
                return true;
            }           
        }       
    }
    return false;
}

//funcao para guardar as peças quando estas chegam ao fim do campo de jogo
function createMatrix(w, h){
    const matrix = [];
    while(h--){ //enquanto h != 0
        matrix.push(new Array(w).fill(0)); //preenche o array de zeros.
    }
    return matrix;
}

//Desenho das peças num array
//As peças sao criadas em 3x3 pois conseguimos fazer uma rotacao mais presisa
function criarPecas(type){
    if (type === 'T') { // peça T 
        return[
            [0, 0, 0],
            [1, 1, 1],
            [0, 1, 0],
        ];
    } else if (type === 'O') { // peça O, criamos esta peça de maneira diferente pois a rotacao nao interfere
        return[
            [2, 2],
            [2, 2],
        ];
    } else if (type === 'L') { // peça L 
        return[
            [0, 3, 0],
            [0, 3, 0],
            [0, 3, 3],
        ];
    } else if (type === 'J') { // peça J
        return[
            [0, 4, 0],
            [0, 4, 0],
            [4, 4, 0],
        ];
    } else if (type === 'I') { // peça I, nesta peca apenas presisamos de 2 posicoes, para toda a rotacao
        return[
            [0, 5, 0, 0],
            [0, 5, 0, 0],
            [0, 5, 0, 0],
            [0, 5, 0, 0],
        ];
    } else if (type === 'S') { // peça S 
        return[
            [0, 6, 6],
            [6, 6, 0],
            [0, 0, 0],
        ];
    } else if (type === 'Z') { // peça Z
        return[
            [7, 7, 0],
            [0, 7, 7],
            [0, 0, 0],
        ];
    }
    
}


//Desenha a peca onde queremos e da clean para que nao se veja a copia anterior da peça
function desenho() {

    //criacao do espaço de jogo
    context.fillStyle = '#000'; //cor
    context.fillRect(0, 0, canvas.width, canvas.height); //tamanho
    
    desenhoMatrix(arena, {x: 0,y: 0}); //pinta as posicao no campo de jogo que estejam ocupadas
    desenhoMatrix(player.matrix, player.pos);
}

//funcao para a pintura da peça
function desenhoMatrix(matrix, offset) {
    matrix.forEach((row, y) => {
        row.forEach((value, x) => {
            if (value !== 0) {

                context.fillStyle = cores[value]; //cor da peça
                context.fillRect(x + offset.x, //locar onde e peça aparece
                    y + offset.y,
                    1, 1);
            }
        });
    });
}

//funcao que mete os dados da peça que o jogador esta a controlar no array do campo
function merge(arena,player){ 
    player.matrix.forEach((row,y) => {
        row.forEach((value, x) =>{
            if (value !== 0){
                arena[y + player.pos.y][x + player.pos.x] = value;
            }
        });
    });
}


function playerDrop(){
    player.pos.y++;
    if (colisao(arena, player)){ //caso haja colisao a posicao da peça e guardade no array de campo de jogo.
        player.pos.y--;
        merge(arena,player);
        randompiece();
        // depois de metermos uma peca e aparecer outra, chamamos a funcao para verificar se existe alguma linha completa
        
        apagaLinha();
        
        /*
        Linha de codigo introduzida na funcao randompiece
        player.pos.y = 0;   //e criada uma nova peca na posicao inicial definida
        */
        updateScore();
    }
    dropCounter = 0; //fazemos isto para que a peça nao desca logo apos a baixarmos, mas so apos 1 segundo depois de clicarmos
}

//Funcao criada para impedir que o jogador saia do campo de jogo quando anda com a peça para a esqueda ou direita
//e que ao colidir com uma peça ao lado, a sau posicao tb nao seja guardada
function jogadorMov(dir){
    player.pos.x += dir;
    if(colisao(arena,player)){
        player.pos.x -= dir;
    }
}

//funcao para as peças serem random
function randompiece(){
    const pecas = 'TOLJISZ';
    player.matrix = criarPecas(pecas[pecas.length * Math.random() | 0])
    player.pos.y = 0;
    player.pos.x = (arena[0].length / 2 | 0) -
                   (player.matrix[0].length / 2 | 0);
    /*
    Presisamos que algo nos diga que o jogador tem pecas no campo ate a primeira linha e que o jogo pare de gerar peças
    pois o jogador perdeu
    */
    
    if (colisao(arena, player)){
        arena.forEach(row => row.fill(0));
    
        alert("Fim do jogo \nResultado: " + player.score);
        
        player.score = 0;
        updateScore();
        
        lvl +=1;
        counter = 0;
    
    }
    
}


// funcao que chama a funcao para rotacao dando apenas a direcao
function jogRodar(dir){
    const pos = player.pos.x;
    let offset = 1; // variavel para andar com a peça para o lado caso haja colisao
    Rodar(player.matrix,dir);
    //verificas se ao rodar a peça nao estamos a fazer colisao, no entanto temos de verificar a direcao da colisao
    while(colisao(arena,player)){
        player.pos.x += offset;
        offset = -(offset + (offset > 0 ? 1 : -1));
        if (offset > player.matrix[0].length) {
            Rodar(player.matrix, -dir);
            player.pos.x = pos;
            return;
        }
    }
}



//Rotacao das pecas
/*
Rotacao para a direita
    c1 c2 c3
l1 [ ][ ][ ]   [ ][x][ ]
l2 [x][x][x]   [x][x][ ]
l3 [ ][x][ ]   [ ][x][ ]

l1 = c3 && l2 = c2 && l3 = c1

Rotacao para a direita
    c1 c2 c3
l1 [ ][x][ ]   [ ][x][ ]
l2 [x][x][ ]   [x][x][x]
l3 [ ][x][ ]   [ ][ ][ ]

l1 = c3 && l2 = c2 && l3 = c1

Rotacao para a direita
    c1 c2 c3
l1 [ ][x][ ]   [ ][x][ ]
l2 [x][x][x]   [ ][x][x]
l3 [ ][ ][ ]   [ ][x][ ]

l1 = c3 && l2 = c2 && l3 = c1

Rotacao para a direita
    c1 c2 c3
l1 [ ][x][ ]   [ ][ ][ ]
l2 [ ][x][x]   [x][x][x]
l3 [ ][x][ ]   [ ][x][ ]

l1 = c3 && l2 = c2 && l3 = c1

tambem podemos fazer a rotacao de uma maneira diferente que e como se fosse um espelho, trocando assim apenas 2 linhas ou colunas

*/
function Rodar(matrix,dir){
    for(let y = 0; y < matrix.length; ++y){
        for (let x = 0; x < y; ++x){
            [
                matrix[x][y],
                matrix[y][x],
            ] = [
                matrix[y][x],
                matrix[x][y],
            ];
        }
    }
    
    if(dir > 0){
        matrix.forEach(row => row.reverse());
    } else {
        matrix.reverse();
    }
}


let dropCounter = 0; //contador para fazer a peça cair
let dropInterval = 1000; //tempo para fazermos o drop da peça

let lastTime = 0;

function update(time = 0) {
    const deltaTime = time - lastTime;
    lastTime = time;

    dropCounter += deltaTime; //mete o tempo no dropcounter
    if (dropCounter > dropInterval) { //caso seja maior que 1000 milisegundos
        /*
        player.pos.y++; //a peça desce uma posicao
        dropCounter = 0;
        
        Repeticao de codigo, substituicao por a funcao
        */
        playerDrop();
    }

    desenho();
    requestAnimationFrame(update);
}

const cores = [
    null,
    '#1eb737',
    '#2dfa80',
    '#22a6d9',
    '#7437fc',
    '#831e00',
    '#58c03e',
    '#c97917',
]

const arena = createMatrix(12,20); //array do campo de jogo para guardar os dados de onde estao as peças ja em campo.

//criamos uma const chamada player para esta entao chamar a funcao desenhomatrix
const player = {
    pos: {
        x: 5,
        y: 0
    },
    matrix: null,
    score: 0,
}


//funcao que actualiza a pontuacao no ecran
function updateScore(){
    document.getElementById('score').innerText = player.score;
}

//funcao que actualiza a lvl no ecran
function updatelvl(){
    document.getElementById('lvl').innerText = lvl;
}



//Controlos de teclas por code
document.addEventListener('keydown', event => {
    /* console.log(event);
    Linha de codigo para que ao verificar a consola no browser, podessemos retirar o codigo das teclas
    link : http://pomle.github.io/keycode/
    */

    if (event.keyCode === 37) { //tecla 37 - seta para a esquerda
        //player.pos.x--;
        jogadorMov(-1);
    } else if (event.keyCode === 39) { //tecla 39 - seta para a direita
        //player.pos.x++;
        jogadorMov(1);
    } else if (event.keyCode === 40){ //tecla 39 - seta para baixo
        /*
        player.pos.y++;
        dropCounter = 0;
        
        Fazemos isto para que a peça faça reset ao timer
        Como temos o prop em 2 lugar com o mesmo codigo, criamos uma funcao para que assim nao seja presiso estar a repetir o mesmo codigo varias vezes
        */
        
        playerDrop();    
    } else if (event.keyCode === 90){ // tecla 90 - Z - rotacao para a esquerda
        jogRodar(-1);
    } else if (event.keyCode === 88){ // tecla 88 - X - rotacao para a direita
        jogRodar(1);
    }

});



randompiece();
updateScore();
update();
