<#import 'layouts/base.ftl' as base>
<@base.main title="main" board_name=card.title>
    <a class="btn btn-danger" href="/card/${card.id}/archived">Archive card</a>
    <div class="row">
        <div class="col-4">
            <h3>ToDos</h3>
            <div class=" card" style="width: 18rem;">
                <div class="card-body">
                    <table border="1">
                        <thead>
                        <tr>
                            <td>Description</td>
                            <td>Date</td>
                            <td>Status</td>
                            <td>Change</td>
                            <td>Delete</td>
                        </tr>
                        </thead>
                        <tbody>
                        <#list card.todos as todo>
                            <tr>
                                <td>${todo.description}</td>
                                <td>${todo.expiredDate}</td>
                                <#if todo.state>
                                    <td>in progress</td>
                                <#else>
                                    <td>complete</td>
                                </#if>
                                <td><a class="btn btn-primary" href="/todo/${todo.id}/update">Change</a></td>
                                <td><a class="btn btn-danger" href="/todo/${todo.id}/delete">Delete</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <a class="btn btn-success" href="/todo/${card.id}/create">New Todo</a>
                </div>
            </div>
        </div>
        <div class="col-4">
            <h3>Files</h3>
            <#list card.files as file>
                <a class="btn btn-primary" target="_blank" href="/dist/img/${file.savedFilename}">${file.filename}</a>
            </#list>
            <form enctype="multipart/form-data" method="post" class="cabinet__inner" action="/card/${card.id}/file">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="file" name="file" accept="image/*" class="change__photo">
                <input type="submit" value="submit">
            </form>
        </div>
        <div class="col-4">
            <h3>Comments</h3>
            <table border="1">
                <thead>
                <tr>
                    <td>user</td>
                    <td>text</td>
                </tr>
                </thead>
                <#list card.comments as comment>
                    <tbody>
                    <#list card.comments as comment>
                        <tr>
                            <td>${comment.user.email}</td>
                            <td>${comment.text}</td>
                        </tr>
                    </#list>
                    </tbody>
                </#list>
            </table>
            <form method="post" class="cabinet__inner" action="/card/${card.id}/comment">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <textarea name="text" class="form-control"></textarea>
                <input type="submit" value="submit"/>
            </form>
        </div>
    </div>
    <ul>
        <#list card.users as u>
            <li>${u.email}</li>
        </#list>
    </ul>
</@base.main>
