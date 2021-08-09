xquery version "1.0";

<Resultado>{
for $autores in doc("grupoNoticias.xml")//Noticia/Autor
order by $autores
return 

	<nome> {data($autores)} </nome>
	
}</Resultado>