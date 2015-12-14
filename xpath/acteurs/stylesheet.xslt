<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/|*">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="text()|@*">
		<xsl:value-of select="."/>
	</xsl:template>
	<xsl:template match="processing-instruction()|comment()"/>

	<xsl:output method="html" encoding="utf-8" indent="yes"/>






	<xsl:template match="/">
		<xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd"&gt;</xsl:text>
		<head>
			<title>Les acteurs</title>
			<meta charset="UTF-8" />
			<link rel="stylesheet" href="acteurs.css" />
		</head>
		<body>
			<div class="pagelarge">
				<h1>Les meilleurs acteurs du monde</h1>
				<fieldset class="sommaire">
					<legend>Index des acteurs</legend>
					<ul>
						<xsl:apply-templates select="//prenom"/>
					</ul>
				</fieldset>
				<hr />
				<xsl:apply-templates select="//acteur" />
			</div>
		</body>
	</xsl:template>

	<xsl:template match="prenom">
		<li>
			<a>
				<xsl:attribute name="href">
					#<xsl:value-of select="."/>
				</xsl:attribute>
				<xsl:value-of select="." />
				<xsl:value-of select="../nom" />
			</a>
		</li>
	</xsl:template>

	<xsl:template match="acteur">
		<h2>
			<a>
				<xsl:attribute name="name">
					<xsl:value-of select="./prenom" />
				</xsl:attribute>
				<xsl:value-of select="./prenom" />
				<xsl:value-of select="./nom" />
			</a>
		</h2>
		<h3>Information</h3>
		<table>
			<tbody>
				<tr>
					<th>Prénom</th>
					<td><xsl:value-of select="./prenom" /></td>
				</tr>
				<tr>
					<th>Nom</th>
					<td><xsl:value-of select="./nom" /></td>
				</tr>
				<tr>
					<th>Date de naissance</th>
					<td><xsl:value-of select="./naissance" /></td>
				</tr>
				<tr>
					<th>Nationalité</th>
					<td><xsl:value-of select="./nationalite" /></td>
				</tr>
			</tbody>
		</table>
		<h3>Biographie</h3>
		<p>
			<xsl:apply-templates select="./biographie//p" />
		</p>
	</xsl:template>

	<xsl:template match="p">
		<xsl:value-of select="./text()" />
		<xsl:apply-templates select="./annee" />
		<xsl:apply-templates select="./film" />
	</xsl:template>

	<xsl:template match="p/annee">
		<em><xsl:value-of select="." /></em>
	</xsl:template>

	<xsl:template match="p/film">
		<em><xsl:value-of select="." /></em>
	</xsl:template>
</xsl:stylesheet>
