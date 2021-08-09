breed[comiloes comilao]
breed[limpadores limpador]

turtles-own [energia]
limpadores-own [Residuos Lixonormal Lixotoxico]

globals [ReposicaoAlimento ResiduosDepositados]

to Setup

  clear-all
  set ReposicaoAlimento 0
  set ResiduosDepositados 0
  reset-ticks
  setup_ambiente
  setup_turtles

end


to Go

  set ReposicaoAlimento 0 ; Contador de alimentos reset

  MoveComiloes
  MoveLimpadores

  ask n-of ReposicaoAlimento patches with [pcolor = 0 ] [ set pcolor green ] ; Reposicao dos alimentos consumidos pelos agentes

  checkDeath ; Verifica se algum agente esta com energia a 0 e mata-o

  tick

end


to setup_ambiente

  ; Lixo Normal
  ask n-of (count patches * PlixoNormal / 100) patches [ set pcolor yellow ]

  ; Lixo Toxico
  ask n-of (count patches * PlixoToxico / 100) patches with [pcolor = 0 ] [ set pcolor red ]

  ; Alimento
  ask n-of (count patches * PAlimento / 100) patches with [pcolor = 0 ] [ set pcolor green ]

  ; Depositos
  ask n-of Depositos patches with [pcolor = 0 ] [ set pcolor blue ]

end

to setup_turtles

  create-limpadores nlimpadores
  [
    Setup_Limpadores
  ]

  create-comiloes ncomiloes
  [
    Setup_Comiloes
  ]

end






; --- LIMPADORES ---

to Setup_Limpadores

  set shape "turtle"
  set color white
  setxy random-pxcor random-pycor
  set heading  0
  set energia EnergiaInicial
  while [[pcolor] of patch-here != 0][setxy random-pxcor random-pycor]

end


to MoveLimpadores

  ask limpadores
  [
    ifelse [pcolor] of patch-here = green and energia < Energiainicial * 2
    [
      ; Comer e ganhar energia dependendo do lixo que carrega ( 100% se menos de metade do limite que podem carregar ou 50% se tiver mais de metade do limite)
      ifelse Residuos < ResiduosLimite / 2
      [set energia energia + EnergiaAlimento]
      [set energia energia + EnergiaAlimento / 2]

      ask patch-here[set pcolor 0]
      set ReposicaoAlimento ReposicaoAlimento + 1
    ]
    [
      ifelse Residuos < ResiduosLimite
      [
        ifelse [pcolor] of patch-here = yellow or [pcolor] of patch-here = red
        [
          ifelse [pcolor] of patch-here = yellow and Residuos < ResiduosLimite
          [
            ask patch-here[set pcolor 0]
            set Residuos Residuos + 1
            set LixoNormal LixoNormal + 1
          ]
          [
            if [pcolor] of patch-here = red and Residuos + 2 <= ResiduosLimite
            [
              ask patch-here[set pcolor 0]
              set Residuos Residuos + 2
              set LixoToxico LixoToxico + 1
            ]
          ]
        ]
        [
          if [pcolor] of patch-here = blue
          [
            set ResiduosDepositados ResiduosDepositados + Residuos
            set Energia Energia + Residuos * 10
            set Residuos 0
            ask n-of Lixonormal patches with [pcolor = 0 ] [ set pcolor yellow ] ; Reposicao do lixo normal depositado por o limpador
            ask n-of Lixotoxico patches with [pcolor = 0 ] [ set pcolor red ] ; Reposicao do lixo toxico depositado por o limpador
            set Lixonormal 0
            set Lixotoxico 0
          ]
        ]
      ]
      [
        if [pcolor] of patch-here = blue
        [
          set ResiduosDepositados ResiduosDepositados + Residuos
          set Energia Energia + Residuos * 10
          set Residuos 0
          ask n-of Lixonormal patches with [pcolor = 0 ] [ set pcolor yellow ] ; Reposicao do lixo normal depositado por o limpador
          ask n-of Lixotoxico patches with [pcolor = 0 ] [ set pcolor red ] ; Reposicao do lixo toxico depositado por o limpador
          set Lixonormal 0
          set Lixotoxico 0
        ]
      ]
    ]

    ; Movimento do Limpador e random com 15% para rodar para um dos lados e 70% andar em frente
    let x random 101
    ifelse x < 15[rt -90 set energia energia - 1]
    [
      ifelse x < 30[rt 90 set energia energia - 1][fd 1 set energia energia - 1]
    ]
  ]

end







; --- COMILOES ---

to Setup_Comiloes

  set color white
  set shape "arrow"
  setxy random-pxcor random-pycor
  set heading  0
  set energia EnergiaInicial
  while [[pcolor] of patch-here != 0][setxy random-pxcor random-pycor]

end


to MoveComiloes

  ask comiloes
  [
    ;verifcar se existe lixo para fazer a reducao de energia
    ifelse [pcolor] of patch-left-and-ahead 90 1 = yellow[set energia energia - (energia / 100 * 5)];reduzir energia em 5%
      [
        if [pcolor] of patch-left-and-ahead 90 1 = red[set energia energia - (energia / 100 * 10)];reduzir energia em 10%
      ]

    ifelse [pcolor] of patch-ahead 1 = yellow[set energia energia - (energia / 100 * 5)];reduzir energia em 5%
      [
        if [pcolor] of patch-ahead 1 = red[set energia energia - (energia / 100 * 10)];reduzir energia em 10%
      ]

    ifelse [pcolor] of patch-right-and-ahead 90 1 = yellow[set energia energia - (energia / 100 * 5)];reduzir energia em 5%
      [
        if [pcolor] of patch-right-and-ahead 90 1 = red[set energia energia - (energia / 100 * 10)];reduzir energia em 10%
      ]

    ; Verifica se existe alimento no seu campo de visão pois é dada propriedade caso exista
    ; Verifica se existem 3 alimentos ele faz random 33%
    ifelse [pcolor] of patch-ahead 1 = green and [pcolor] of patch-left-and-ahead 90 1 = green and [pcolor] of patch-right-and-ahead 90 1 = green
    [
      let x random 101
      ifelse x < 33 [rt 90]
      [
        ifelse x < 66 [rt -90]
        [
        fd 1 set energia energia - 1
        set energia energia + EnergiaAlimento
        ask patch-here[set pcolor 0]
        set ReposicaoAlimento ReposicaoAlimento + 1
        ]
      ]
    ]
    [ ; Verifica se existem 2 alimentos (Esquerda/Frente)
      ifelse [pcolor] of patch-left-and-ahead 90 1 = green and [pcolor] of patch-ahead 1 = green
      [
        let x random 101
        ifelse x < 50 [rt -90]
        [
        fd 1 set energia energia - 1
        set energia energia + EnergiaAlimento
        ask patch-here[set pcolor 0]
        set ReposicaoAlimento ReposicaoAlimento + 1
        ]
      ]
      [ ; Verifica se existem 2 alimentos (Direita/Frente)
        ifelse [pcolor] of patch-right-and-ahead 90 1 = green and [pcolor] of patch-ahead 1 = green
        [
          let x random 101
          ifelse x < 50 [rt 90]
          [
          fd 1 set energia energia - 1
          set energia energia + EnergiaAlimento
          ask patch-here[set pcolor 0]
          set ReposicaoAlimento ReposicaoAlimento + 1
          ]
        ]
        [ ; Verifica se existem 2 alimentos (Esquerda/Direita)
          ifelse [pcolor] of patch-left-and-ahead 90 1 = green and [pcolor] of patch-right-and-ahead 90 1 = green
          [
            let x random 101
            ifelse x < 50 [rt -90][rt 90]
          ]
          [ ; Verifica se existe alimentos à sua frente
            ifelse [pcolor] of patch-ahead 1 = green
            [
              fd 1 set energia energia - 1
              set energia energia + EnergiaAlimento
              ask patch-here[set pcolor 0]
              set ReposicaoAlimento ReposicaoAlimento + 1
            ]
            [  ; Verifica se existe alimentos à sua Esquerda
              ifelse [pcolor] of patch-left-and-ahead 90 1 = green [rt -90];
              [ ; Verifica se existe alimentos à sua Direira
                ifelse [pcolor] of patch-right-and-ahead 90 1 = green [rt 90]
                [
                  ;No caso de nao haver alimento, verificamos se existe lixo e dependendo do numero dos mesmos e onde estão fazemos um movimento para nos afastar
                  ifelse ([pcolor] of patch-ahead 1 = red or [pcolor] of patch-ahead 1 = yellow) and ([pcolor] of patch-left-and-ahead 90 1 = red or [pcolor] of patch-left-and-ahead 90 1 = yellow) and ([pcolor] of patch-right-and-ahead 90 1 = red or [pcolor] of patch-right-and-ahead 90 1 = yellow)
                  [; Se houver 3 lixos fazemos uma rotacao à Esquera ou Direita
                    let x random 101
                    ifelse x < 50 [rt 90][rt -90]
                  ]
                  [; Se houver lixo na casa da Esquera e à Frente rodamos a direita
                    ifelse ([pcolor] of patch-left-and-ahead 90 1 = red or [pcolor] of patch-left-and-ahead 90 1 = yellow) and ([pcolor] of patch-ahead 1 = red or [pcolor] of patch-ahead 1 = yellow)
                    [rt 90];
                    [; Se houver lixo na casa da Direita e à Frente rodamos a esquerda
                      ifelse ([pcolor] of patch-right-and-ahead 90 1 = red or [pcolor] of patch-right-and-ahead 90 1 = yellow) and ([pcolor] of patch-ahead 1 = red or [pcolor] of patch-ahead 1 = yellow)
                      [rt -90]
                      [; Se houver lixo na casa da Esquera e Direita andamos em frente
                        ifelse ([pcolor] of patch-left-and-ahead 90 1 = red or [pcolor] of patch-left-and-ahead 90 1 = yellow) and ([pcolor] of patch-right-and-ahead 90 1 = red or [pcolor] of patch-right-and-ahead 90 1 = yellow)
                        [fd 1 set energia energia - 1]
                        [; Se apenas existe um lixo à Esquerda, andamos em frente ou rodamos a direita
                          ifelse [pcolor] of patch-left-and-ahead 90 1 = red or [pcolor] of patch-left-and-ahead 90 1 = yellow
                          [
                            let x random 101
                            ifelse x < 50 [rt 90][fd 1 set energia energia - 1]
                          ]
                          [; Se apenas existe um lixo à Direita, andamos em frente ou rodamos a esquerda
                            ifelse [pcolor] of patch-right-and-ahead 90 1 = red or [pcolor] of patch-right-and-ahead 90 1 = yellow
                            [
                              let x random 101
                              ifelse x < 50 [rt -90][fd 1 set energia energia - 1]
                            ]
                            [; Se apenas existe um lixo à Frente, rodamos a esquerda ou direita
                              ifelse [pcolor] of patch-ahead 1 = red or [pcolor] of patch-ahead 1 = yellow
                              [
                                let x random 101
                                ifelse x < 50 [rt 90][rt -90]
                              ]
                              [; Caso nao exista nada a sua volta temos 10% para rodar esquerda ou direita e 80% para andar em frente
                                let x random 101
                                ifelse x < 10 [rt -90]
                                [
                                  ifelse x < 20 [rt 90][fd 1 set energia energia - 1]
                                ]
                              ]
                            ]
                          ]
                        ]
                      ]
                    ]
                  ]
                ]
              ]
            ]
          ]
        ]
      ]
    ]
  ]
end

to checkDeath

  ask comiloes
   [
     if energia < 1 [die] ; Se um comilao tiver menos de 1 de energia, morre
   ]

  ask limpadores
   [
     if energia < 1 ; Se um limpador tiver menos de 1 de energia, morre
      [
        ask n-of Lixonormal patches with [pcolor = 0 ] [ set pcolor yellow ] ; Reposicao do lixo normal depositado por o limpador
        ask n-of Lixotoxico patches with [pcolor = 0 ] [ set pcolor red ] ; Reposicao do lixo toxico depositado por o limpador
        die
      ]
   ]

end










@#$#@#$#@
GRAPHICS-WINDOW
786
107
1289
611
-1
-1
15.0
1
10
1
1
1
0
1
1
1
-16
16
-16
16
1
1
1
ticks
30.0

BUTTON
896
54
960
87
Setup
setup
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

SLIDER
420
452
592
485
PlixoNormal
PlixoNormal
0
15
5.0
1
1
NIL
HORIZONTAL

SLIDER
602
453
774
486
PlixoToxico
PlixoToxico
0
15
5.0
1
1
NIL
HORIZONTAL

MONITOR
1329
129
1406
174
Lixo Normal
count patches with [pcolor = yellow]
17
1
11

MONITOR
1413
129
1487
174
Lixo Toxico
count patches with [pcolor = red]
17
1
11

SLIDER
420
561
592
594
PAlimento
PAlimento
5
20
10.0
1
1
NIL
HORIZONTAL

MONITOR
1378
181
1444
226
Alimentos
count patches with [pcolor = green]
1
1
11

SLIDER
503
355
675
388
Depositos
Depositos
1
10
10.0
1
1
NIL
HORIZONTAL

SLIDER
411
151
583
184
Ncomiloes
Ncomiloes
1
50
15.0
1
1
NIL
HORIZONTAL

SLIDER
597
151
777
184
Nlimpadores
Nlimpadores
0
50
10.0
1
1
NIL
HORIZONTAL

BUTTON
1129
58
1192
91
Go
Go\nif ticks = 10000[stop]\nif count turtles = 0 [stop]
T
1
T
OBSERVER
NIL
-
NIL
NIL
1

SLIDER
415
269
587
302
EnergiaInicial
EnergiaInicial
50
150
100.0
1
1
NIL
HORIZONTAL

SLIDER
597
269
769
302
ResiduosLimite
ResiduosLimite
10
50
25.0
1
1
NIL
HORIZONTAL

SLIDER
601
562
773
595
EnergiaAlimento
EnergiaAlimento
1
50
25.0
1
1
NIL
HORIZONTAL

TEXTBOX
474
120
782
138
Numero de Comiloes e Limpadores\n
16
0.0
1

TEXTBOX
434
403
787
446
Percentagem de Lixo normal e Lixo toxico no ambiente
16
0.0
1

TEXTBOX
471
324
760
343
Numero de Depositos no ambiente
16
0.0
1

TEXTBOX
438
210
769
252
Energia Inicial e Limite de resicuos a carregar por Limpador
16
0.0
1

TEXTBOX
457
506
738
549
Percentagem de Alimento no ambiente e energia que o alimento fornece
16
0.0
1

MONITOR
1348
248
1532
293
Numero de Limpadores (tartarugas)
count limpadores
17
1
11

MONITOR
1357
300
1524
345
Numero de Comiloes (setas)
count comiloes
17
1
11

PLOT
1322
363
1606
622
Residuos depositados pelos limpadores 
NIL
NIL
0.0
10.0
0.0
10.0
true
false
"" ""
PENS
"Lixo Recolhido" 1.0 0 -2674135 true "" "plot ResiduosDepositados"

MONITOR
1512
156
1604
201
Lixo Recolhido
ResiduosDepositados
17
1
11

@#$#@#$#@
# Ambiente

## Tipos de residuos 

	Lixo normal - celulas amarelas
	Lixo toxico - celulas vermelhas

	% de celulas com residuos (cada um) é 0%-15% (slider)


## Tipos de alimento

	Alimento - celulas verdes

	% de celulas com alimento (cada um) é 5%-20% (slider)

	enegia ao "comer" um alimento varia 1-50 (slider)
	

## Depositos para depositar o lixo

	Celulas azuis

	numero de depositos 1-10 (slider)

**ATENCAO**

**O alimento e os resíduos devem reaparecer no mundo de tal forma que os níveis configurados se mantenham ao longo da simulação.**
 

# Agentes

## Objetivos comuns dos agentes

	Encontrar alimento para sobreviver
	Todos recebem o mesmo valor de energia ao ser criados
	Cada interacao (tick) perdem uma unidade de energia
	Caso a energia chegue a 0 o agente morre

#### Movimento
	
	Cada uma destas ações conta como uma iteracao. 
	
	• Andar para a frente
	• Rodar 90º para a Esq. ou Dir.

## Comiloes (Slider)

 	São agentes reativos, sem memoria.
	Perdem energia ao tocar ou percecionar algum lixo.

### Caracteristicas


	FALTA AS CARACTERISTICAS 

	Conseguem percecionar as celulas à sua frente, esquerda e direita.
	
	[ ][•][ ]
	[•][☺][•]


## Limpadores (Slider)

	São agentes reativos, com memoria.
	Limpar os residuos

### Caracteristicas

	
	FALTA AS CARACTERISTICAS 
	
	Conseguem percecionar as celulas à sua frente e direita.
	
	[ ][•][ ]
	[ ][☺][•]
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

ghost
false
0
Polygon -7500403 true true 30 165 13 164 -2 149 0 135 -2 119 0 105 15 75 30 75 58 104 43 119 43 134 58 134 73 134 88 104 73 44 78 14 103 -1 193 -1 223 29 208 89 208 119 238 134 253 119 240 105 238 89 240 75 255 60 270 60 283 74 300 90 298 104 298 119 300 135 285 135 285 150 268 164 238 179 208 164 208 194 238 209 253 224 268 239 268 269 238 299 178 299 148 284 103 269 58 284 43 299 58 269 103 254 148 254 193 254 163 239 118 209 88 179 73 179 58 164
Line -16777216 false 189 253 215 253
Circle -16777216 true false 102 30 30
Polygon -16777216 true false 165 105 135 105 120 120 105 105 135 75 165 75 195 105 180 120
Circle -16777216 true false 160 30 30

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

person service
false
0
Polygon -7500403 true true 180 195 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285
Polygon -1 true false 120 90 105 90 60 195 90 210 120 150 120 195 180 195 180 150 210 210 240 195 195 90 180 90 165 105 150 165 135 105 120 90
Polygon -1 true false 123 90 149 141 177 90
Rectangle -7500403 true true 123 76 176 92
Circle -7500403 true true 110 5 80
Line -13345367 false 121 90 194 90
Line -16777216 false 148 143 150 196
Rectangle -16777216 true false 116 186 182 198
Circle -1 true false 152 143 9
Circle -1 true false 152 166 9
Rectangle -16777216 true false 179 164 183 186
Polygon -2674135 true false 180 90 195 90 183 160 180 195 150 195 150 135 180 90
Polygon -2674135 true false 120 90 105 90 114 161 120 195 150 195 150 135 120 90
Polygon -2674135 true false 155 91 128 77 128 101
Rectangle -16777216 true false 118 129 141 140
Polygon -2674135 true false 145 91 172 77 172 101

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

sheep
false
15
Circle -1 true true 203 65 88
Circle -1 true true 70 65 162
Circle -1 true true 150 105 120
Polygon -7500403 true false 218 120 240 165 255 165 278 120
Circle -7500403 true false 214 72 67
Rectangle -1 true true 164 223 179 298
Polygon -1 true true 45 285 30 285 30 240 15 195 45 210
Circle -1 true true 3 83 150
Rectangle -1 true true 65 221 80 296
Polygon -1 true true 195 285 210 285 210 240 240 210 195 210
Polygon -7500403 true false 276 85 285 105 302 99 294 83
Polygon -7500403 true false 219 85 210 105 193 99 201 83

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

squirrel
false
0
Polygon -7500403 true true 87 267 106 290 145 292 157 288 175 292 209 292 207 281 190 276 174 277 156 271 154 261 157 245 151 230 156 221 171 209 214 165 231 171 239 171 263 154 281 137 294 136 297 126 295 119 279 117 241 145 242 128 262 132 282 124 288 108 269 88 247 73 226 72 213 76 208 88 190 112 151 107 119 117 84 139 61 175 57 210 65 231 79 253 65 243 46 187 49 157 82 109 115 93 146 83 202 49 231 13 181 12 142 6 95 30 50 39 12 96 0 162 23 250 68 275
Polygon -16777216 true false 237 85 249 84 255 92 246 95
Line -16777216 false 221 82 213 93
Line -16777216 false 253 119 266 124
Line -16777216 false 278 110 278 116
Line -16777216 false 149 229 135 211
Line -16777216 false 134 211 115 207
Line -16777216 false 117 207 106 211
Line -16777216 false 91 268 131 290
Line -16777216 false 220 82 213 79
Line -16777216 false 286 126 294 128
Line -16777216 false 193 284 206 285

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

wolf
false
0
Polygon -16777216 true false 253 133 245 131 245 133
Polygon -7500403 true true 2 194 13 197 30 191 38 193 38 205 20 226 20 257 27 265 38 266 40 260 31 253 31 230 60 206 68 198 75 209 66 228 65 243 82 261 84 268 100 267 103 261 77 239 79 231 100 207 98 196 119 201 143 202 160 195 166 210 172 213 173 238 167 251 160 248 154 265 169 264 178 247 186 240 198 260 200 271 217 271 219 262 207 258 195 230 192 198 210 184 227 164 242 144 259 145 284 151 277 141 293 140 299 134 297 127 273 119 270 105
Polygon -7500403 true true -1 195 14 180 36 166 40 153 53 140 82 131 134 133 159 126 188 115 227 108 236 102 238 98 268 86 269 92 281 87 269 103 269 113

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270
@#$#@#$#@
NetLogo 6.1.0
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180
@#$#@#$#@
0
@#$#@#$#@
