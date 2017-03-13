# jHTML2Md
A simple converter from HTML to Markdown in Java.

I created this project for importing notes on Capsa Notes.

Currently it hasn't any options. I plan to add different markdown styles (Headers using # instead of underline =, for example).

## How to use it:

It's pretty simple, first add jSoup to the classpath. Then:

String markdownText = HTML2Md.convert(html, baseURL);
Where html is a String containing the html code you want to convert, and baseURL is the url you will use as a reference for converting relative links.

You can use directly an URL too, like this:

    URL url = new URL("http://www.example.com/");
    HTML2Md.convert(url, 30000);

The 30000 is the timeout for requesting the page in milliseconds.

Enjoy!

# RoadMap

* Add options for different markdown styles
    * Jekyll & Hexo markdown styles has already been added, to use them, simply invoke

    HTML2Md.htmlToJekyllMd(htmlPath, mdPath, charset);
    HTML2Md.htmlToHexoMd(htmlPath, mdPath, charset);

* Some refactoring, currently the code is quite ugly
