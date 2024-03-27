package com.fyp.mutrade.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
//Web configuration class.
@Component
@PropertySource(value="classpath:site.properties")
public class SiteConfig {
	
	@Value("${fypmumarket.site.name}")
	private String siteName;
	@Value("${fypmumarket.site.url}")
	private String siteUrl;
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	
	
}
