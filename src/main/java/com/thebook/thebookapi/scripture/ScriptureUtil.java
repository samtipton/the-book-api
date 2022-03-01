package com.thebook.thebookapi.scripture;

public class ScriptureUtil {

    public static int formatScriptureId(final String... bookChapterVerses) {
        final StringBuilder sb = new StringBuilder();
        sb.append(bookChapterVerses[0]);

        if (bookChapterVerses.length == 1) {
            sb.append("001");
            sb.append("001");
        } else if (bookChapterVerses.length == 2) {
            padChapterVerse(bookChapterVerses[1], sb);
            sb.append("001");
        } else if (bookChapterVerses.length == 3) {
            padChapterVerse(bookChapterVerses[1], sb);
            padChapterVerse(bookChapterVerses[2], sb);
        }
        return Integer.parseInt(sb.toString());
    }

    private static void padChapterVerse(final String chapterVerse, final StringBuilder sb) {
        final int chapterVerseInt = Integer.parseInt(chapterVerse);
        if (chapterVerseInt < 1000 && chapterVerseInt > 99) {
            sb.append(chapterVerse);
        } else if (chapterVerseInt < 100 && chapterVerseInt > 9) {
            sb.append("0");
            sb.append(chapterVerse);
        } else {
            sb.append("00");
            sb.append(chapterVerse);
        }
    }
}
