package net.kiranatos;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo01 {

    public static void main(String[] args) {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://htmlunit.sourceforge.io/gettingStarted.html");

            System.out.println("\n\ntitle:" + page.getTitleText());

            String pageAsXml = page.asXml();
            String pageAsText = page.asNormalizedText();

            // make very heigh window:
            webClient.getCurrentWindow().setInnerHeight(60000);

            //get list of all divs
            final DomNodeList<DomNode> divs = page.querySelectorAll("div");
            for (DomNode div : divs) {
                System.out.println(div.hasAttributes());
            }

            //get div which has the id 'breadcrumbs'
            final DomNode div = page.querySelector("#bodyColumn > section:nth-child(4) > section:nth-child(4) > div > pre");
            System.out.println(div.asXml());

        } catch (IOException ex) {
            Logger.getLogger(Demo01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FailingHttpStatusCodeException ex) {
            Logger.getLogger(Demo01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
