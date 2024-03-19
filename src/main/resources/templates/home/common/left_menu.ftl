    <nav class="ease2">
    <ul>
        <li class="blank-head">
            <a href="/home/index/index"></a>
        </li>
      	
      	<li class="category-12 catg">
            <a href="/home/index/index" target="_top">
                <i class="nav-icons">
                    <img src="/home/imgs/12.png" alt="All Ads"></i>
                <h3>All Ads</h3>
           </a>
        </li>
      	<#list adsCategorys as adsCategory>
        <#if adsCategory.parent??>
        <#else>
        <li class="category-${adsCategory.id} catg">
            <a href="/home/ads/list?cid=${adsCategory.id}" target="_top">
                <i class="nav-icons">
                    <img src="/photo/view?filename=${adsCategory.icon}" alt="${adsCategory.name}"></i>
                <h3>${adsCategory.name}</h3>
            </a>
            <div class="sub-nav">
                <span>
                    <#list adsCategorys as secondAdsCategory>
                    <#if secondAdsCategory.parent??>
                    <#if secondAdsCategory.parent.id == adsCategory.id>
                    <a href="/home/ads/list?cid=${secondAdsCategory.id}" target="_top">${secondAdsCategory.name}</a>
                   	</#if>
                   	</#if>
                    </#list>
                </span>
            </div>
        </li>
        </#if>
        </#list>
    </ul>
</nav>