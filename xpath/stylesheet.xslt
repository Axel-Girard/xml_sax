<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/|*">
	  <xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="text()|@*">
	  <xsl:value-of select="."/>
	</xsl:template>
	<xsl:template match="processing-instruction()|comment()"/>

	<xsl:output method="xml" encoding="utf-8" indent="yes"/>





	<xsl:template match="/actu">
  		<xsl:apply-templates select="breve">
      		<xsl:sort select="@theme"/>
      		<xsl:apply-templates/>
  		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="photo">
		<img>
		    <xsl:attribute name="src">
		        <xsl:value-of select="./@src"/>
		    </xsl:attribute>
		</img>
	</xsl:template>

	<xsl:template match="url">
		<a>
		    <xsl:attribute name="href">
		        <xsl:value-of select="./@href"/>
		    </xsl:attribute>
		</a>
	</xsl:template>
</xsl:stylesheet>
