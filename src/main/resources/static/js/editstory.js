$(document).ready(function(){
    var currentidstory = window.location.search.split("?idstory=")[1];
    var currentfirstScene = "";
    getStory(currentidstory);
    getAllScene(currentidstory);

    $(document).on("click", ".scene-edit", function(){
        editScene($(this).parent().parent().attr("id"));
    });

    $(".story-save").click(function(){
        $.ajax({
            type: "POST",
            url: "http://localhost:5366/api/story/edit/" + currentidstory,
            contentType: "application/json",
            headers: {login: "brenn", password: "admin", id: currentidstory, title: $("#story-edit-title").val(), description: $("#story-edit-text").val(), isPublic: $("#story-edit-isPublic").is(":checked"), loginAuthor: "brenn"},
            success: function () {
                location.reload();
            },
            error: function () {
                throw new Error("Error updating story.");
            }
        });
    });
});

function getStory(idstory) {
    $.ajax({
        type: "GET",
        url: "http://localhost:5366/api/story/get/" + idstory,
        headers: {login: "brenn", password: "admin"},
        success: function (data) {
            currentfirstScene = data["firstScene"]["id"];
            $("#story-edit-title").val(data["title"]);
            $("#story-edit-text").text(data["description"]);
            $("#story-edit-image").val(data["firstScene"]["imageURL"]);
            $("#story-edit-isPublic").attr("checked", '"' + data["isPublic"] + '"');
        },
        error: function () {
            throw new Error("Error loading story.");
        }
    });
}

function getAllScene(idstory){
    $.ajax({
        type: "GET",
        url: "http://localhost:5366/api/story/get/"+idstory+"/scenes",
        headers: {login: "brenn", password: "admin"},
        success: function(data){
            for(var key in data){
                $(".scene-list").append("<div id='"+data[key]["id"]+"' class='col-lg-4 col-md-6 col-sm-12 scene-container'><div class='scene bg-dark-primary-color'></div><div class='scene-text'></div></div>");
                $(".scene-list #" + data[key]["id"] + " .scene-text").html("<h4>"+data[key]["title"]+"</h4><p class='mb-2'>"+data[key]["description"]+"</p><button class='btn mb-4 secondary-color scene-edit'>Editer</button>");
            }
        },
        error: function(){
            throw new Error("Error loading scenes.");
        }
    });
}

function editScene(idscene){
    window.location = "editscene.html?idscene="+idscene;
}