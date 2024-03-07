<footer>
    <img class="footer-tri" src="/home/imgs/footer-tri.png">
        <div class="friend-links">
        <div class="links-title center">Related site</div>
        <ul class="links-wr center" style="margin-top: 22px;">
            <#if friendLinkList??>
            <#list friendLinkList as friendLink>
            <li class="first">
                <a href="${friendLink.url}" target="_blank" class="links">${friendLink.name}</a>
            </li>
            </#list>
            </#if>
            <li class="first">
                <a href="/system/index" target="_blank" class="links">Admin Portal</a>
            </li>
        </ul>
    </div>
    <div class="site-msg line2">
        <span class="power">${siteSetting.allRights!""}</span>
    </div>
</footer>