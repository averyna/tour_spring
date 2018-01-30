function validate() {
    if (document.f.username.value == "" && document.f.password.value == "") {
        alert("${noUser} and ${noPass}");
        document.f.username.focus();
        return false;
    }
    if (document.f.username.value == "") {
        alert("${noUser}");
        document.f.username.focus();
        return false;
    }
    if (document.f.password.value == "") {
        alert("${noPass}");
        document.f.password.focus();
        return false;
    }
}


//<bean id="messageSource"
//class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
//    <property name="basename" value="messages"/>
//    <property name="defaultEncoding" value="UTF-8" />
//    <property name="fallbackToSystemLocale" value="false" />
//</bean>
//
//<bean id="localeChangeInterceptor" class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
//    <property name="paramName" value="lang" />
//    </bean>
//
//<bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver">
//    <property name="defaultLocale" value="ru"/>
//</bean>

//
//in messages.properties
//
//message.username=Username required
//message.password=Password required
//message.unauth=Unauthorized access!!
//message.badCredentials=Invalid username or password
//message.sessionExpired=Session timed out
//message.logoutError=Sorry, error login out
//message.logoutSucc=You logged out successfully