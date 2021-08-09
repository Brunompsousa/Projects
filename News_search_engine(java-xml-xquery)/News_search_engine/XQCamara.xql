xquery version "1.0";

<Resultado>{

for $x in doc("grupoMunicipios.xml")//Municipio, $b in doc("pesquisa.xml")
where contains($x/Nome,$b)
return 
	<Municipio>
		<Número> {data($x/Número)} </Número>
		<Nome> {data($x/Nome)} </Nome>
		<Presidente> {data($x/Presidente)} </Presidente>
		<Mail> {data($x/Mail)} </Mail>
		<site> {data($x/site)} </site>
		<Telefone> {data($x/Telefone)} </Telefone>
		<Freguesias> {data($x/Freguesias)} </Freguesias>
		<Area> {data($x/Area)} </Area>
		<Habitantes> {data($x/Habitantes)} </Habitantes>
		<imagem> {data($x/imagem)} </imagem>
	</Municipio>

}</Resultado>
