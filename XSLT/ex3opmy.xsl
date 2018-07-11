<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">

    <xsl:output method="html" indent="yes"></xsl:output>

    <xsl:template match = "/BlogApplication">
        <html><head><title>BlogApplication</title></head>
        <body>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>PostTitle</th>
                <th>CommentId</th>
                <th>CommentContent</th>
            </tr>

            <xsl:for-each select="BlogUsers/BlogUser">
                <xsl:variable name="username" select="UserName"></xsl:variable>
                <xml:choose>
                    <xsl:when test="count(/BlogApplication/BlogPosts/BlopPost/Comments/Comment[UserName=$username])=0">
                        <tr>
                            <td>
                                <xsl:value-of select="$username"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="FirstName"></xsl:value-of>
                            </td>
                            <td/>
                            <td/>
                            <td/>
                        </tr>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:for-each /BlogApplication/BlogPosts/BlogPost/Comments/Comment[UserName=$username]>
                        <tr>
                            <td>
                                <xsl:value-of select="$username"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="$username/../FirstName"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="../../Title"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="CommentId"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="Content"></xsl:value-of>
                            </td>
                        </tr>
                    </xsl:for-each>
                    </xsl:otherwise>
                </xml:choose>
                
            </xsl:for-each>
        </table>
    </body>
</html>
    </xsl:template>
</xsl:stylesheet>