package com.packt.webstore.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Erling Molde on 03.11.2016.
 */
public class PromoCodeInterceptor extends HandlerInterceptorAdapter {

    private String promoCode;
    private String errorRedirect;
    private String offerRedirect;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String givenPromoCode = request.getParameter("promo");

        if (promoCode.equals(givenPromoCode)) {
            response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
        } else {
            response.sendRedirect(errorRedirect);
        }
        return false;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setErrorRedirect(String errorRedirect) {
        this.errorRedirect = errorRedirect;
    }

    public void setOfferRedirect(String offerRedirect) {
        this.offerRedirect = offerRedirect;
    }
}