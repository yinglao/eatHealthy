(: Version :)
xquery version "1.0";
(: Variable declarations. You can also declare functions, namespaces, etc. :)
declare variable $document as xs:string := "BlogApplication.xml";


(: To run, just remove the comments characters for each FLWOR statement. :)


(: Simple loop. :)

<ul>
{
for $i in (1 to 10)
return <li> {$i} </li>
}
</ul>



(: Function. :)
(:
<p name="function">
{
avg(
  for $i in (1,2,3)
  return $i
)
}
</p>
:)


(: Get the first name of username3. :)
(:
<p name="flwr">
{
for $user in doc($document)/BlogApplication/BlogUsers/BlogUser
let $firstname := $user/FirstName
where $user/UserName/text() = "username3"
return $firstname/text()
}
</p>
:)


(: Same as above, but with more XPath. :)
(:
<p name="lr">
{
let $firstname := doc($document)/BlogApplication/BlogUsers/BlogUser[UserName="username3"]/FirstName/text()
return $firstname
}
</p>
:)


(: Order first names. :)
(:
<p name="order">
<ul>
{
for $user in doc($document)/BlogApplication/BlogUsers/BlogUser
let $firstname := $user/FirstName
order by $firstname/text() descending
return <li> {$firstname/text()} </li>
}
</ul>
</p>
:)


(:
Multiple for, let clauses. Same as first XSLT example, but
INNER JOIN (intersection) and unrolled.
For each BlogUser, list all their BlogPosts.
:)
(:
Note: is this for clause actually needed? We can just use one
for clause, like one for-each loop in the XSLT solution, plus
a let clause with the appropriate XPath expression.
However, this example uses two for clauses to demonstrate
how to use multiple for clauses in a FLWOR statement.
:)
(:
<p name="multiple">
      <table border="1">
         <tr>
            <th>UserName</th>
            <th>FirstName</th>
            <th>PostId</th>
            <th>PostTitle</th>
         </tr>
         {
         for $user in doc($document)/BlogApplication/BlogUsers/BlogUser
         for $post in doc($document)/BlogApplication/BlogPosts/BlogPost
         let $username := $user/UserName
         let $firstname := $user/FirstName
         let $postid := $post/PostId
         let $bpusername := $post/UserName
         let $title := $post/Title
         where $username/text() = $bpusername/text()
         order by $username/text() ascending, $postid ascending
         return
         <tr>
           <td>{$username/text()}</td>
           <td>{$firstname/text()}</td>
           <td>{$postid/text()}</td>
           <td>{$title/text()}</td>
         </tr>
         }
      </table>
</p>
:)



