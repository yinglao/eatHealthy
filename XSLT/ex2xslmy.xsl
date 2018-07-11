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

            <xsl:for-each select="BlogPosts/BlogPost/Comments/Comment">
                <xsl:variable name="username" select="UserName"></xsl:variable>
                <xsl:variable name="user" select="/BlogApplication/BlogUsers/BlogUser[UserName=$username]"></xsl:variable>
                <tr>
                    <td>
                        <xsl:value-of select="$username"></xsl:value-of>
                    </td>
                    <td>
                        <xsl:value-of select="$user/FirstName"></xsl:value-of>
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
        </table>
    </body>
</html>
    </xsl:template>
</xsl:stylesheet>