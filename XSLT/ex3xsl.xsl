<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:output method="html" indent="yes"></xsl:output>
    
    <xsl:template match="/BlogApplication">
        
        <html><head><title>BlogApplication</title></head><body>
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
                    <xsl:variable name="firstname" select="FirstName"></xsl:variable>
                    
                    <xsl:choose>
                        <xsl:when test="count(/BlogApplication/BlogPosts/BlogPost/Comments/Comment[UserName/text()=$username]) = 0">
                        <tr>
                            <td>
                                <xsl:value-of select="$username/text()"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="$firstname/text()"></xsl:value-of>
                            </td>
                            <td/>
                            <td/>
                            <td/>
                        </tr>
                        </xsl:when>

                        <xsl:otherwise>

                            <xsl:for-each select="/BlogApplication/BlogPosts/BlogPost/Comments[count(Comment) > 0]/Comment[UserName/text()=$username]">
                                <xsl:variable name="posttitle" select="../../Title"></xsl:variable>
                                <xsl:variable name="commentid" select="CommentId"></xsl:variable>
                                <xsl:variable name="commentcontent" select="Content"></xsl:variable>
                                <tr>
                                    <td>
                                        <xsl:value-of select="$username/text()"></xsl:value-of>
                                    </td>
                                    <td>
                                        <xsl:value-of select="$firstname/text()"></xsl:value-of>
                                    </td>
                                    <td>
                                        <xsl:value-of select="$posttitle/text()"></xsl:value-of>
                                    </td>
                                    <td>
                                        <xsl:value-of select="$commentid/text()"></xsl:value-of>
                                    </td>
                                    <td>
                                        <xsl:value-of select="$commentcontent/text()"></xsl:value-of>
                                    </td>
                                </tr>
                            </xsl:for-each>
                       </xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>
            </table>
        </body></html>
    </xsl:template>
    
</xsl:stylesheet>
