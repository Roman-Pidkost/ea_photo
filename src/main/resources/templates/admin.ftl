<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>EA-admin-panel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/css/admin.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="/js/admin.min.js"></script>
</head>
<body>
<div class="row">
    <div id="tabs" class="col-xs-12 col-sm-10 col-md-8 col-sm-offset-1 col-md-offset-2">
        <ul>
            <li><a href="#tabs-1">Photos</a></li>
            <li><a href="#tabs-2">Categories</a></li>
            <li><a href="#tabs-3">Backstages</a></li>
        </ul>
        <div id="tabs-1" class="row">
            <form class="form-horizontal col-xs-12" id="form" method="post" action="/admin/photo" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="category" class="col-xs-2">Category</label>
                    <div class="col-xs-10">
                    <#list categories as category>
                        <label for="category${category.id}">${category.name}</label>
                        <input type="radio" required id="category${category.id}" value="${category.id}" name="category" class="categories">
                    <#else>
                        <h5 class="text-danger">There is no categories, you should create some</h5>
                    </#list>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="tags" class="col-xs-2 control-label">Tags</label>
                    <div class="col-xs-10">
                        <input type="text" required name="tags" id="tags" class="form-control" placeholder="Separated by spaces">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="priority" class="col-xs-2 control-label">Priority</label>
                    <div class="col-xs-10">
                        <input type="text" required name="priority" id="priority" class="form-control" placeholder="Priority">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-9 col-xs-offset-2">
                        <input type="file" required name="photos" multiple id="files" accept="image/jpeg,image/png" class="form-control">
                        <button>OK</button>
                    </div>
                </div>
                <h4>Photos</h4>
                <div class="photos-container">
                <#list photos as photo>
                    <div>
                        <img src="${photo.photo}" title="Priority:${photo.priority}; Category:${photo.category.name}">
                        <a alt="${photo.id}">&#x2a2f;</a>
                    </div>
                <#else>
                    The list is empty
                </#list>
                </div>
            </form>
        </div>
        <div id="tabs-2" class="row">
            <form class="form-horizontal col-xs-12" id="form" method="post" action="/admin/category" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="name" class="col-xs-2 control-label">Category</label>
                    <div class="col-xs-10">
                        <input type="text" required name="name" id="name" class="form-control" placeholder="Category name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="priority" class="col-xs-2 control-label">Priority</label>
                    <div class="col-xs-10">
                        <input type="text" required name="priority" id="priority" class="form-control" placeholder="Priority">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-9 col-xs-offset-2">
                        <button>OK</button>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <h4>Categories</h4>
                <#list categories as category>
                    <button class="categories-item" alt="${category.id}" title="Priority:${category.priority}">${category.name} <span>&#x2a2f;</span></button>
                <#else>
                    The list is empty
                </#list>
            </div>
        </div>
        <div id="tabs-3" class="row">
            <form class="form-horizontal col-xs-12" id="form" method="post" action="/admin/backstage" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="name" class="col-xs-2 control-label">Name</label>
                    <div class="col-xs-10">
                        <input type="text" required name="name" id="name" class="form-control" placeholder="Backstage name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="priority" class="col-xs-2 control-label">Priority</label>
                    <div class="col-xs-10">
                        <input type="text" required name="priority" id="priority" class="form-control" placeholder="Priority">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="mainPhoto" class="col-xs-2 control-label">Main photo</label>
                    <div class="col-xs-10">
                        <input type="file" required name="mainPhoto" id="mainPhoto" accept="image/jpeg,image/png" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="photos" class="col-xs-2 control-label">Photos</label>
                    <div class="col-xs-10">
                        <input type="file" required name="photos" multiple id="photos" accept="image/jpeg,image/png" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-9 col-xs-offset-2">
                        <button>OK</button>
                    </div>
                </div>
            </form>
            <h4>Backstages</h4>
            <div class="backstages-container">
                <#list backstages as backstage>
                    <div>
                        <div class="main-img-container">
                            <img src="${backstage.mainPhoto}" title="Priority:${backstage.priority}; Name:${backstage.name}">
                            <a alt="${backstage.id}">&#x2a2f;</a>
                        </div>
                        <div class="backstage-photos">
                            <#list backstage.backstagePhotos as photo>
                                <img src="${photo.photo}"">
                            </#list>
                        </div>
                    </div>
                <#else>
                    The list is empty
                </#list>
            </div>
        </div>
    </div>
</div>
<p id="res"></p>
</body>
</html>