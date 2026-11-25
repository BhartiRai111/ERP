package com.dataman.base.serial;

import com.dataman.base.util.Strings;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

/**
 * Expands the number format of numberSystemFormat.nsFormat (e.g. "{vouchersname}/{year}/{counter}").
 * Tokens (lower case, same as legacy): sitesname site voucherprefix company year yearfull month day voucher
 * vouchersname startyear endyear fullstartyear fullendyear, plus caller supplied extras; {counter} last.
 */
public final class RecIdFormat {

    private RecIdFormat() {
    }

    /** Everything except the counter; the result still has no {counter} text left in it. */
    public static String prefix(String nsFormat, Inputs in, Map<String, String> extra) {
        String f = nsFormat;
        f = f.replace("{sitesname}", Strings.xNull(in.siteShortName()));
        f = f.replace("{site}", pad(3, in.siteCode()));
        f = f.replace("{voucherprefix}", pad(2, in.voucherPrefix()));
        f = f.replace("{company}", Strings.xNull(in.companyShortName()));
        f = f.replace("{year}", two(in.date().getYear()));
        f = f.replace("{yearfull}", pad(4, in.date().getYear()));
        f = f.replace("{month}", pad(2, in.date().getMonthValue()));
        f = f.replace("{day}", pad(2, in.date().getDayOfMonth()));
        f = f.replace("{voucher}", pad(3, in.voucherType()));
        f = f.replace("{vouchersname}", Strings.xNull(in.voucherShortName()));
        f = f.replace("{startyear}", two(in.start().getYear()));
        f = f.replace("{endyear}", two(in.end().getYear()));
        f = f.replace("{fullstartyear}", pad(4, in.start().getYear()));
        f = f.replace("{fullendyear}", pad(4, in.end().getYear()));
        if (extra != null) {
            for (Map.Entry<String, String> e : extra.entrySet()) {
                f = f.replace("{" + e.getKey() + "}", Strings.xNull(e.getValue()).trim());
            }
        }
        return f.replace("{counter}", "");
    }

    /** Final number: the format with every token and the counter (formatted with counterFormat, e.g. "0000"). */
    public static String value(String nsFormat, Inputs in, Map<String, String> extra, String counterFormat, long counter) {
        String withTokens = prefix(nsFormat.replace("{counter}", "\u0000"), in, extra);
        return withTokens.replace("\u0000", new DecimalFormat(counterFormat).format(counter));
    }

    private static String two(int year) {
        return pad(2, year % 100);
    }

    private static String pad(int width, Object number) {
        return new DecimalFormat("0".repeat(width)).format(Strings.iNull(number));
    }

    /** Values the tokens are built from. */
    public record Inputs(String siteCode, String siteShortName, String companyShortName, String voucherType,
            String voucherPrefix, String voucherShortName, LocalDate date, LocalDate start, LocalDate end) {
    }
}
