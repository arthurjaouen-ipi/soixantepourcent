$(document).ready(function(){
	// getAllStories();

	$(".story").hover(function(){
		var parent = $(this).parent();
		$(".story-text", parent).css("display","block");
	});

	$(".story-text").mouseleave(function(){
		$(this).css("display","none");
	});
});

function getAllStories(){
	$.ajax({
		type: "GET",
		url: "",
		success: function(data){
			for(var key in data){
				$(".stories-list").append("<div id='" + data[key][idStory] + "' class='col-lg-4 col-md-6 col-sm-12 story-container'><div class='story'></div><div class='story-text'></div></div>");
				$(".stories-list #" + data[key][idStory] + " .story").css("background", data[key][imgStory] + "  no-repeat center fixed").css("background-size", "cover");
				$(".stories-list #" + data[key][idStory] + " .story-text").text(data[key][txtStory] + "</br><button class='btn story-read'>Lire</button>");
			}
		},
		error: function(){
			alert("Erreur au chargement des histoires.");
			throw new Error("Error loading stories.");
		}
	});
}