<footer>
    <img class="footer-tri" src="/home/imgs/footer-tri.png">
        <div class="friend-links">
        <div class="links-title center">Related site</div>
        <ul class="links-wr center" style="margin-top: 22px;">
            <#if relatedSiteList??>
            <#list relatedSiteList as relatedSite>
            <li class="first">
                <a href="${relatedSite.url}" target="_blank" class="links">${relatedSite.name}</a>
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