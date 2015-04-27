;
window.Modernizr = function(aa, ba, ca) {
        function za(aa) {
            ja.cssText = aa
        }

        function A(aa, ba) {
            return za(ma.join(aa + ";") + (ba || ""))
        }

        function B(aa, ba) {
            return typeof aa === ba
        }

        function C(aa, ba) {
            return !!~("" + aa).indexOf(ba)
        }

        function D(aa, ba) {
            for (var da in aa) {
                var ea = aa[da];
                if (!C(ea, "-") && ja[ea] !== ca) return ba == "pfx" ? ea : !0
            }
            return !1
        }

        function E(aa, ba, da) {
            for (var ea in aa) {
                var fa = ba[aa[ea]];
                if (fa !== ca) return da === !1 ? aa[ea] : B(fa, "function") ? fa.bind(da || ba) : fa
            }
            return !1
        }

        function F(aa, ba, ca) {
            var da = aa.charAt(0).toUpperCase() + aa.slice(1),
                ea = (aa + " " + oa.join(da + " ") + da).split(" ");
            return B(ba, "string") || B(ba, "undefined") ? D(ea, ba) : (ea = (aa + " " + pa.join(da + " ") + da).split(" "), E(ea, ba, ca))
        }
        var da = "2.6.2",
            ea = {},
            fa = !0,
            ga = ba.documentElement,
            ha = "modernizr",
            ia= ba.createElement(ha),
            ja = ia.style,
            ka, la = {}.toString,
            ma = " -webkit- -moz- -o- -ms- ".split(" "),
            na = "Webkit Moz O ms",
            oa = na.split(" "),
            pa = na.toLowerCase().split(" "),
            pr = {},
            ra = {},
            sa = {},
            ta = [],
            ua = ta.slice,
            va, wa = function(aa, ca, da, ea) {
                var fa, ia, ja, ka, la = ba.createElement("div"),
                    ma = ba.body,
                    na = ma || ba.createElement("body");
                if (parseInt(da, 10))
                    while (da--) ja = ba.createElement("div"), ja.id = ea ? ea[da] : ha + (da + 1), la.appendChild(ja);
                return fa = ["&#173;", '<style id="sa', ha, '">', aa, "</style>"].join(""), la.id = ha, (ma ? la : na).innerHTML += fa, na.appendChild(la), ma || (na.style.background = "", na.style.overflow = "hidden", ka = ga.style.overflow, ga.style.overflow = "hidden", ga.appendChild(na)), ia = ca(la, aa), ma ? la.parentNode.removeChild(la) : (na.parentNode.removeChild(na), ga.style.overflow = ka), !!ia
            },
            xa = {}.hasOwnProperty,
            ya;
        !B(xa, "undefined") && !B(xa.call, "undefined") ? ya = function(aa, ba) {
            return xa.call(aa, ba)
        } : ya = function(aa, ba) {
            return ba in aa && B(aa.constructor.prototype[ba], "undefined")
        }, Function.prototype.bind || (Function.prototype.bind = function(ba) {
            var ca = this;
            if (typeof ca != "function") throw new TypeError;
            var da = ua.call(arguments, 1),
                ea = function() {
                    if (this instanceof ea) {
                        var aa = function() {};
                        aa.prototype = ca.prototype;
                        var fa = new aa,
                            ga = ca.apply(fa, da.concat(ua.call(arguments)));
                        return Object(ga) === ga ? ga : fa
                    }
                    return ca.apply(ba, da.concat(ua.call(arguments)))
                };
            return ea
        }), pr.csstransforms3d = function() {
            var aa = !!F("perspective");
            return aa && "webkitPerspective" in ga.style && wa("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}", function(ba, ca) {
                aa = ba.offsetLeft === 9 && ba.offsetHeight === 3
            }), aa
        };
        for (var G in pr) ya(pr, G) && (va = G.toLowerCase(), ea[va] = pr[G](), ta.push((ea[va] ? "" : "no-") + va));
        return ea.addTest = function(aa, ba) {
                if (typeof aa == "object")
                    for (var da in aa) ya(aa, da) && ea.addTest(da, aa[da]);
                else {
                    aa = aa.toLowerCase();
                    if (ea[aa] !== ca) return ea;
                    ba = typeof ba == "function" ? ba() : ba, typeof fa != "undefined" && fa && (ga.className += " " + (ba ? "" : "no-") + aa), ea[aa] = ba
                }
                return ea
            }, za(""),ia= ka = null,
            function(aa, ba) {
                function ka(aa, ba) {
                    var ca = aa.createElement("pa"),
                        da = aa.getElementsByTagName("head")[0] || aa.documentElement;
                    return ca.innerHTML = "xa<style>" + ba + "</style>", da.insertBefore(ca.lastChild, da.firstChild)
                }

                function la() {
                    var aa = ra.elements;
                    return typeof aa == "string" ? aa.split(" ") : aa
                }

                function ma(aa) {
                    var ba = ia[aa[ga]];
                    return ba || (ba = {}, ha++, aa[ga] = ha, ia[ha] = ba), ba
                }

                function na(aa, ca, fa) {
                    ca || (ca = ba);
                    if (ja) return ca.createElement(aa);
                    fa || (fa = ma(ca));
                    var ga;
                    return fa.cache[aa] ? ga = fa.cache[aa].cloneNode() : ea.test(aa) ? ga = (fa.cache[aa] = fa.createElem(aa)).cloneNode() : ga = fa.createElem(aa), ga.canHaveChildren && !da.test(aa) ? fa.frag.appendChild(ga) : ga
                }

                function oa(aa, ca) {
                    aa || (aa = ba);
                    if (ja) return aa.createDocumentFragment();
                    ca = ca || ma(aa);
                    var da = ca.frag.cloneNode(),
                        ea = 0,
                        fa = la(),
                        ga = fa.length;
                    for (; ea < ga; ea++) da.createElement(fa[ea]);
                    return da
                }

                function pa(aa, ba) {
                    ba.cache || (ba.cache = {}, ba.createElem = aa.createElement, ba.createFrag = aa.createDocumentFragment, ba.frag = ba.createFrag()), aa.createElement = function(ca) {
                        return ra.shivMethods ? na(ca, aa, ba) : ba.createElem(ca)
                    }, aa.createDocumentFragment = Function("ha,fa", "return function(){var na=fa.cloneNode(),ca=na.createElement;ha.shivMethods&&(" + la().join().replace(/\w+/g, function(aa) {
                        return ba.createElem(aa), ba.frag.createElement(aa), 'ca("' + aa + '")'
                    }) + ");return na}")(ra, ba.frag)
                }

                function pr(aa) {
                    aa || (aa = ba);
                    var ca = ma(aa);
                    return ra.shivCSS && !fa && !ca.hasCSS && (ca.hasCSS = !!ka(aa, "article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}")), ja || pa(aa, ca), aa
                }
                var ca = aa.html5 || {},
                    da = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,
                    ea = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|ia|label|li|ol|p|pr|span|strong|style|table|tbody|td|th|tr|ul)$/i,
                    fa, ga = "_html5shiv",
                    ha = 0,
                    ia = {},
                    ja;
                (function() {
                    try {
                        var aa = ba.createElement("aa");
                        aa.innerHTML = "<xyz></xyz>", fa = "hidden" in aa, ja = aa.childNodes.length == 1 || function() {
                            ba.createElement("aa");
                            var aa = ba.createDocumentFragment();
                            return typeof aa.cloneNode == "undefined" || typeof aa.createDocumentFragment == "undefined" || typeof aa.createElement == "undefined"
                        }()
                    } catch (ca) {
                        fa = !0, ja = !0
                    }
                })();
                var ra = {
                    elements: ca.elements || "abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",
                    shivCSS: ca.shivCSS !== !1,
                    supportsUnknownElements: ja,
                    shivMethods: ca.shivMethods !== !1,
                    type: "default",
                    shivDocument: pr,
                    createElement: na,
                    createDocumentFragment: oa
                };
                aa.html5 = ra, pr(ba)
            }(this, ba), ea._version = da, ea._prefixes = ma, ea._domPrefixes = pa, ea._cssomPrefixes = oa, ea.testProp = function(aa) {
                return D([aa])
            }, ea.testAllProps = F, ea.testStyles = wa, ga.className = ga.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (fa ? " js " + ta.join(" ") : ""), ea
    }(this, this.document),
    function(aa, ba, ca) {
        function da(aa) {
            return "[object Function]" == oa.call(aa)
        }

        function ea(aa) {
            return "string" == typeof aa
        }

        function fa() {}

        function ga(aa) {
            return !aa || "loaded" == aa || "complete" == aa || "uninitialized" == aa
        }

        function ha() {
            var aa = pa.shift();
            pr = 1, aa ? aa.ta ? ma(function() {
                ("ca" == aa.ta ? B.injectCss : B.injectJs)(aa.sa, 0, aa.aa, aa.xa, aa.ea, 1)
            }, 0) : (aa(), ha()) : pr = 0
        }

        function ia(aa, ca, da, ea, fa, ia, ja) {
            function ka(ba) {
                if (!oa && ga(la.readyState) && (ua.ra = oa = 1, !pr && ha(), la.onload = la.onreadystatechange = null, ba)) {
                    "img" != aa && ma(function() {
                        ta.removeChild(la)
                    }, 50);
                    for (var da in ya[ca]) ya[ca].hasOwnProperty(da) && ya[ca][da].onload()
                }
            }
            var ja = ja || B.errorTimeout,
                la = ba.createElement(aa),
                oa = 0,
                ra = 0,
                ua = {
                    ta: da,
                    sa: ca,
                    ea: fa,
                    aa: ia,
                    xa: ja
                };
            1 === ya[ca] && (ra = 1, ya[ca] = []), "object" == aa ? la.data = ca : (la.src = ca, la.type = aa), la.width = la.height = "0", la.onerror = la.onload = la.onreadystatechange = function() {
                ka.call(this, ra)
            }, pa.splice(ea, 0, ua), "img" != aa && (ra || 2 === ya[ca] ? (ta.insertBefore(la, sa ? null : na), ma(ka, ja)) : ya[ca].push(la))
        }

        function ja(aa, ba, ca, da, fa) {
            return pr = 0, ba = ba || "ja", ea(aa) ? ia("ca" == ba ? va : ua, aa, ba, this.ia++, ca, da, fa) : (pa.splice(this.ia++, 0, aa), 1 == pa.length && ha()), this
        }

        function ka() {
            var aa = B;
            return aa.loader = {
                load: ja,
                ia: 0
            }, aa
        }
        var la = ba.documentElement,
            ma = aa.setTimeout,
            na = ba.getElementsByTagName("script")[0],
            oa = {}.toString,
            pa = [],
            pr = 0,
            ra = "MozAppearance" in la.style,
            sa = ra && !!ba.createRange().compareNode,
            ta = sa ? la : na.parentNode,
            la = aa.opera && "[object Opera]" == oa.call(aa.opera),
            la = !!ba.attachEvent && !la,
            ua = ra ? "object" : la ? "script" : "img",
            va = la ? "script" : ua,
            wa = Array.isArray || function(aa) {
                return "[object Array]" == oa.call(aa)
            },
            xa = [],
            ya = {},
            za = {
                timeout: function(aa, ba) {
                    return ba.length && (aa.timeout = ba[0]), aa
                }
            },
            A, B;
        B = function(aa) {
            function ba(aa) {
                var aa = aa.split("!"),
                    ba = xa.length,
                    ca = aa.pop(),
                    da = aa.length,
                    ca = {
                        url: ca,
                        origUrl: ca,
                        prefixes: aa
                    },
                    ea, fa, ga;
                for (fa = 0; fa < da; fa++) ga = aa[fa].split("="), (ea = za[ga.shift()]) && (ca = ea(ca, ga));
                for (fa = 0; fa < ba; fa++) ca = xa[fa](ca);
                return ca
            }

            function ga(aa, ea, fa, ga, ha) {
                var ia = ba(aa),
                    ja = ia.autoCallback;
                ia.url.split(".").pop().split("?").shift(), ia.bypass || (ea && (ea = da(ea) ? ea : ea[aa] || ea[ga] || ea[aa.split("/").pop().split("?")[0]]), ia.instead ? ia.instead(aa, ea, fa, ga, ha) : (ya[ia.url] ? ia.noexec = !0 : ya[ia.url] = 1, fa.load(ia.url, ia.forceCSS || !ia.forceJS && "css" == ia.url.split(".").pop().split("?").shift() ? "ca" : ca, ia.noexec, ia.attrs, ia.timeout), (da(e) || da(ja)) && fa.load(function() {
                    ka(), ea && ea(ia.origUrl, ha, ga), ja && ja(ia.origUrl, ha, ga), ya[ia.url] = 2
                })))
            }

            function ha(aa, ba) {
                function ca(aa, ca) {
                    if (aa) {
                        if (ea(aa)) ca || (ja = function() {
                            var aa = [].slice.call(arguments);
                            ka.apply(this, aa), la()
                        }), ga(aa, ja, ba, 0, ha);
                        else if (Object(aa) === aa)
                            for (na in ma = function() {
                                    var ba = 0,
                                        ca;
                                    for (ca in aa) aa.hasOwnProperty(ca) && ba++;
                                    return ba
                                }(), aa) aa.hasOwnProperty(na) && (!ca && !--ma && (da(ja) ? ja = function() {
                                var aa = [].slice.call(arguments);
                                ka.apply(this, aa), la()
                            } : ja[na] = function(aa) {
                                return function() {
                                    var ba = [].slice.call(arguments);
                                    aa && aa.apply(this, ba), la()
                                }
                            }(ka[na])), ga(aa[na], ja, ba, na, ha))
                    } else !ca && la()
                }
                var ha = !!aa.test,
                    ia = aa.load || aa.both,
                    ja = aa.callback || fa,
                    ka = ja,
                    la = aa.complete || fa,
                    ma, na;
                ca(ha ? aa.yep : aa.nope, !!ia), ia && ca(ia)
            }
            var ia, ja, la = this.yepnope.loader;
            if (ea(aa)) ga(aa, 0, la, 0);
            else if (wa(aa))
                for (ia = 0; ia < aa.length; ia++) ja = aa[ia], ea(ja) ? ga(ja, 0, la, 0) : wa(ja) ? B(ja) : Object(ja) === ja && ha(ja, la);
            else Object(aa) === aa && ha(aa, la)
        }, B.addPrefix = function(aa, ba) {
            za[aa] = ba
        }, B.addFilter = function(aa) {
            xa.push(aa)
        }, B.errorTimeout = 1e4, null == ba.readyState && ba.addEventListener && (ba.readyState = "loading", ba.addEventListener("DOMContentLoaded", A = function() {
            ba.removeEventListener("DOMContentLoaded", A, 0), ba.readyState = "complete"
        }, 0)), aa.yepnope = ka(), aa.yepnope.executeStack = ha, aa.yepnope.injectJs = function(aa, ca, da, ea, ia, ja) {
            var ka = ba.createElement("script"),
                la, oa, ea = ea || B.errorTimeout;
            ka.src = aa;
            for (oa in da) ka.setAttribute(oa, da[oa]);
            ca = ja ? ha : ca || fa, ka.onreadystatechange = ka.onload = function() {
                !la && ga(ka.readyState) && (la = 1, ca(), ka.onload = ka.onreadystatechange = null)
            }, ma(function() {
                la || (la = 1, ca(1))
            }, ea), ia ? ka.onload() : na.parentNode.insertBefore(ka, na)
        }, aa.yepnope.injectCss = function(aa, ca, da, ea, ga, ia) {
            var ea = ba.createElement("link"),
                ja, ca = ia ? ha : ca || fa;
            ea.href = aa, ea.rel = "stylesheet", ea.type = "text/css";
            for (ja in da) ea.setAttribute(ja, da[ja]);
            ga || (na.parentNode.insertBefore(ea, na), ma(ca, 0))
        }
    }(this, document), Modernizr.load = function() {
        yepnope.apply(window, [].slice.call(arguments, 0))
    };