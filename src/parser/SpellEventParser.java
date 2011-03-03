package parser;

import report.LogReport;
import world.Unit;
import event.LogEvent;
import event.SpellEvent;

public class SpellEventParser extends EventParser{

    private final LogReport report;

    public SpellEventParser(LogReport report) {
        this.report = report;
    }

    @Override
    public LogEvent parse(String date, String time, Unit source, Unit target, String[] params) {

        return new SpellEvent(date, time, source, target, report.getSpellManager().parseSpell(params[7], LogParser.parseString(params[8]), LogParser.parseInt(params[9])));
    }

    @Override
    public boolean match(String key) {
        return key.startsWith("SPELL");
    }

}
