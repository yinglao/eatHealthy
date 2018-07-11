<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">

    <xsl:output method="html" indent="yes"></xsl:output>

    <xsl:template match = "/ReviewApplication">
        <html>
            <head>
                <title>HW5.7</title>
            </head>
            <body>
                <h1>Restaurant and Recommendations</h1>
                <table border="1">
                    <tr>
                        <th>Restaurant</th>
                        <th>Recommendations</th>
                    </tr>
                    <xsl:for-each select="Companys/Company/Restaurants/Restaurant">
                        <xsl:variable name="restaurant" select=".">
                        </xsl:variable>
                        <tr>
                            <td>
                                <xsl:value-of select="$restaurant/Name"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="count(/ReviewApplication/Recommendations/Recommendation[RestaurantId=$restaurant/RestaurantId])"></xsl:value-of>
                            </td>
                        </tr>

                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>