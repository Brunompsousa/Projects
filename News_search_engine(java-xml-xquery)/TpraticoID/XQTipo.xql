xquery version "1.0";

<Resultado>{

for $x in doc("grupoNoticias.xml")//Noticia, $b in doc("pesquisa.xml")
where contains($x/Tipo,$b)
return 
	<Noticia>
		<Número> {data($x/Número)} </Número>
		<Tipo> {data($x/Tipo)} </Tipo>
		<NúmeroPub> {data($x/NúmeroPub)} </NúmeroPub>
		<Data> {data($x/Data)} </Data>
		<Autor> {data($x/Autor)} </Autor>
		<Descrição> {data($x/Descrição)} </Descrição>
		<Link> {data($x/Link)} </Link>
	</Noticia>

}</Resultado>
