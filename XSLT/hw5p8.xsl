<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">

    <xsl:output method="html" indent="yes"></xsl:output>

    <xsl:template match = "/ReviewApplication">
        <html>
            <head>
                <title>HW5.8</title>
            </head>
            <body>
                <h1>Reviews for restaurantid1</h1>
                <table border="1">
                    <tr>
                        <th>RestaurantId</th>
                        <th>UserName</th>
                        <th>Rating</th>
                    </tr>
                    <xsl:variable name="restaurantid" select="'restaurantid1'"></xsl:variable>
                    <xsl:for-each select="Reviews/Review[RestaurantId=$restaurantid]">
                        <tr>
                            <td>
                                <xsl:value-of select="$restaurantid"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="UserName"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="Rating"></xsl:value-of>
                            </td>
                        </tr>

                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>