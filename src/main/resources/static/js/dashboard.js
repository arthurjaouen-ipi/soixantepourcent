$(document).ready(function(){
});

$(document).on("click", ".story-edit", function(){
	editStory($(this).parent().parent().attr("id"));
});

function editStory(id){
	window.location = "editstory?id=1";
}