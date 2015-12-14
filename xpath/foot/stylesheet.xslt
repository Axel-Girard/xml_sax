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
			<title>Foot</title>
			<meta charset="UTF-8" />
			<link rel="stylesheet" href="foot.css" />
		</head>
		<body>
			<div class="pagelarge">
				<h1>Résultats de ligue 1, saison 2003-2004</h1>
				<h2>Statistiques sur les conclusions des rencontres</h2>
				<fieldset class="sommaire">
					<legend>Sommaire</legend>
					<ul>
						<xsl:apply-templates select="//JOURNEE/@NUMERO"/>
					</ul>
				</fieldset>
				<table>
					<tbody>
						<tr>
							<th>Victoires de l'équipe à domicile</th>
							<td align="right"><xsl:value-of select="count(//RENCONTRE[@SCORED > @SCOREE and @SCORED != '-' and @SCOREE != '-'])" />/<xsl:value-of select="count(//RENCONTRE)" /></td>
							<td align="right"><xsl:value-of select="round(count(//RENCONTRE[@SCORED > @SCOREE and @SCORED != '-' and @SCOREE != '-']) * 100 div count(//RENCONTRE) * 100) div 100" />%</td>
						</tr>
						<tr>
							<th>Victoires de l'équipe à l'extérieur</th>
							<td align="right"><xsl:value-of select="count(//RENCONTRE[@SCOREE > @SCORED and @SCORED != '-' and @SCOREE != '-'])" />/<xsl:value-of select="count(//RENCONTRE)" /></td>
							<td align="right"><xsl:value-of select="round(count(//RENCONTRE[@SCOREE > @SCORED and @SCORED != '-' and @SCOREE != '-']) * 100 div count(//RENCONTRE) * 100) div 100" />%</td>
						</tr>
						<tr>
							<th>Matches nul</th>
							<td align="right"><xsl:value-of select="count(//RENCONTRE[@SCORED = @SCOREE])" />/<xsl:value-of select="count(//RENCONTRE)" /></td>
							<td align="right"><xsl:value-of select="round(count(//RENCONTRE[@SCORED = @SCOREE]) * 100 div count(//RENCONTRE) * 100) div 100" />%</td>
						</tr>
					</tbody>
				</table>

				<embed width="400" height="400" type="image/svg+xml" src="stats.svg"/>
				<hr />
				
				<xsl:apply-templates select="//JOURNEE[@NUMERO]"/>

			</div>
		</body>
	</xsl:template>

	<xsl:template name="modele-Numero" match="JOURNEE/@NUMERO">
		<li>
			<a>
				<xsl:attribute name="href">
					#<xsl:value-of select="."/>
				</xsl:attribute>
				journée <xsl:value-of select="."/><br />
			</a>
		</li>
	</xsl:template>

	<xsl:template name="modele-JOURNEE2" match="JOURNEE[@NUMERO]">
		<h2>
			<a>
				<xsl:attribute name="name">
					<xsl:value-of select="@NUMERO"/>
				</xsl:attribute>
				Journée <xsl:value-of select="@NUMERO"/><br />
			</a>
		</h2>

		<table width="40%" xmlns="">
			<tbody>
				<xsl:apply-templates select="RENCONTRE"/>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template name="modele-Rencontre" match="RENCONTRE">
		<xsl:apply-templates select="RENCONTRE[@SCORED > @SCOREE]" />
		<xsl:apply-templates select="RENCONTRE[@SCOREE > @SCORED]" />
		<xsl:apply-templates select="RENCONTRE[@SCOREE = @SCORED]" />
	</xsl:template>

	<xsl:template match="RENCONTRE[@SCORED > @SCOREE]">
		<tr>
			<th align="right"><xsl:value-of select="@DOMICILE"/></th>
			<th><xsl:value-of select="@SCORED"/></th>
			<td><xsl:value-of select="@SCOREE"/></td>
			<td align="left"><xsl:value-of select="@EXTERIEUR"/></td>
		</tr>
	</xsl:template>

	<xsl:template match="RENCONTRE[@SCOREE > @SCORED]">
		<tr>
			<td align="right"><xsl:value-of select="@DOMICILE"/></td>
			<td><xsl:value-of select="@SCORED"/></td>
			<th><xsl:value-of select="@SCOREE"/></th>
			<th align="left"><xsl:value-of select="@EXTERIEUR"/></th>
		</tr>
	</xsl:template>

	<xsl:template match="RENCONTRE[@SCOREE = @SCORED]">
		<tr>
			<td align="right"><xsl:value-of select="@DOMICILE"/></td>
			<td><xsl:value-of select="@SCORED"/></td>
			<td><xsl:value-of select="@SCOREE"/></td>
			<td align="left"><xsl:value-of select="@EXTERIEUR"/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
