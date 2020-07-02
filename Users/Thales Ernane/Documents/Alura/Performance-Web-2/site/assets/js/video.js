/*

setImeout(function(){

    var iframe = '<iframe class="elasticMedia" src="https://player.vimeo.com/video/99185150" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>';

    
    document.querySelector('.elasticMedia-container').innerHTML = iframe; 
}, 1500);
*/
/*

// carregamento assíncrono
setTimeout(function(){

    var iframe = '<iframe class="elasticMedia" src="<div class="video-js vjs-default-skin video-player-frame" id="video-player-frame" style="width: 100%;"><iframe class="video-js vjs-tech" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen="" src="https://player.vimeo.com/video/99185150""></iframe></div> frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>';
    document.querySelector('.elasticMedia-container').innerHTML = iframe; // joga o elemento iframe na class elasticMedia-container


}, 1200);

*/


setTimeout(function(){

	var iframe = '<iframe class="elasticMedia" src="https://player.vimeo.com/video/99185150" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>';
	var container = document.querySelector('.elasticMedia-container');
	if (container) container.innerHTML = iframe;

}, 600);