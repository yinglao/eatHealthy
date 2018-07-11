<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">

    <xsl:output method="html" indent="yes"></xsl:output>

    <xsl:template match = "/ReviewApplication">
        <html>
            <head>
                <title>HW5.9</title>
            </head>
            <body>
                <h1>Reviews with Recommendations</h1>
                <table border="1">
                    <tr>
                        <th>RestaurantId</th>
                        <th>UserName</th>
                        <th>Rating</th>
                        <th>Recommendations</th>
                    </tr>
                    <xsl:for-each select="Reviews/Review">
                        <xsl:variable name="restaurantid" select="RestaurantId">
                        </xsl:variable>
                        <xsl:variable name="username" select="UserName"></xsl:variable>
                        <tr>
                            <td>
                                <xsl:value-of select="$restaurantid"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="$username"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="Rating"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="count(/ReviewApplication/Recommendations/Recommendation[RestaurantId = $restaurantid and UserName=$username])"></xsl:value-of>
                            </td>
                        </tr>

                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>