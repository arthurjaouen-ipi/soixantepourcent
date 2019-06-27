$(document).ready(function(){
    getAllAuthorStories();
});

function getAllAuthorStories(){
    $.ajax({
        type: "GET",
        url: "http://localhost:5366/api/story/get",
        headers: {login: "", password: ""},
        success: function(data){
            for(var key in data){
                $(".stories-list").append("<div id='"+data[key]["id"]+"' class='col-lg-4 col-md-6 col-sm-12 story-container'><div class='story'></div><div class='story-text'></div></div>");
                $(".stories-list #" + data[key]["id"] + " .story").css("background", data[key]["firstScene"]["imageUrl"]);
                $(".stories-list #" + data[key]["id"] + " .story-text").html(data[key]["description"] + "</br><button class='btn story-edit'>Editer</button>");

                $("#" + data[key]["id"]).mouseenter(function(){
                    var parent = $(this).parent();
                    $(".story-text", parent).css("display","block");

                    $(".story-text", $(this)).mouseleave(function(){
                        $(this).css("display","none");
                    });
                });
            }
        },
        error: function(){
            throw new Error("Error loading stories.");
        }
    });
}

$(document).on("click", ".story-edit", function(){
	editStory($(this).parent().parent().attr("id"));
});

function editStory(id){
	window.location = "editstory.html?idstory="+id;
}