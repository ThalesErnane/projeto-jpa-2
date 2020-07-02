
var jarodei = false;

// ultilizando lazy load com scroll, para carregar as imgs somente quando descer a pag

window.onscroll = function() {

    // throttle, aborta a execução se ele já foi executado a 100ms
    if(jarodei) return;
    jarodei = true;

    setTimeout (function(){
        jarodei = false;

    }, 100)

    // seleciona todas as imgs que possuem o atributo data-src
    var imgs = document.querySelectorAll('img[data-src]');

    for(var i = 0; i < imgs.length; i++){

     if(imgs[i].getBoundingClientRect().top < window.innerHeight + 200){ //entra no if quando o scroll estiver perto 200 da altura da pag
        imgs[i].src = imgs[i].getAttribute('data-src'); // pega todas as imgs com o atributo data-src e substitui por img.src

     }
    }

}; 

/*
- Versão otimizada

(function(){

    // controle do throttle
    var jarodei = false;

    // pega todas as imagens num array e pre-calcula seu topo
    var imgs = document.querySelectorAll('img[data-src]:not([src])');
    var cache = [];
    for (var i = 0; i < imgs.length; i++) {
        cache.push({
            topo: imgs[i].getBoundingClientRect().top + pageYOffset,
            elemento: imgs[i]
        });
    }

    // cache da altura da janela
    var alturaJanela = window.innerHeight;

    window.addEventListener('scroll', function scrollListener() {

        // throttle
        if (jarodei) return;
        jarodei = true;
        setTimeout(function () { 
            jarodei = false; 
        }, 100);

        // meu while não toca no DOM, observa apenas variáveis cacheadas e o pageYOffset.
        // só manipulo o DOM quando preciso realmente mexer na imagem.
        while (cache.length && cache[0].topo < pageYOffset + alturaJanela + 200) {
            var img = cache.shift().elemento;
            img.src = img.getAttribute('data-src');
        }

        // removo o onscroll se não precisar mais dele
        if (cache.length == 0) {
            window.removeEventListener('scroll', scrollListener);
        }

    });

})();

*/