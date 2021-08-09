xquery version "1.0";

<Resultado>{
let $autores := doc("pesqListaMunic.xml")//Munic
return 
	$autores[position() <= 5]

}</Resultado>