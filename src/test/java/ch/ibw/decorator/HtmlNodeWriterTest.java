package ch.ibw.decorator;

import ch.ibw.HtmlNode;
import ch.ibw.HtmlNodeFactory;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

@RunWith(OleasterRunner.class)
public class HtmlNodeWriterTest {{

  describe("Composition", () -> {
    it("should be possible to create one level nested html", () -> {
      HtmlNode root = HtmlNode.create("html");

      root.addChild(HtmlNodeFactory.createA("about:blank"));
      String html = HtmlNodeWriter.create().write(root);

      expect(html).toEqual("<html><a href='about:blank'></a></html>");
    });

    it("should be possible to create two level nested html", () -> {
      HtmlNode root = HtmlNode.create("html");
      HtmlNode a1 = HtmlNodeFactory.createA("about:blank1");
      HtmlNode a2 = HtmlNodeFactory.createA("about:blank2");
      HtmlNode nestedSpan = HtmlNode.create("span");

      root
              .addChild(a1
                      .addChild(nestedSpan))
              .addChild(a2);

      String html = HtmlNodeWriter.create().write(root);

      expect(html).toEqual(
              "<html>" +
                "<a href='about:blank1'>" +
                "<span></span>" +
                "</a>" +
                "<a href='about:blank2'></a>" +
              "</html>");
    });
  });
}}
