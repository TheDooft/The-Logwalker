package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import report.Fight;
import report.LogReport;
import world.TimeInterval;
import world.Timestamp;
import world.Unit;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import event.LogEvent;

public class LogParser {

    LogReport report;

    List<EventParser> eventParsers = new ArrayList<EventParser>();

    public LogParser(String string) throws FileNotFoundException, java.text.ParseException {

        report = new LogReport();

        initParsers();



        Scanner scanner = new Scanner(new File(string));

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();


            try {
                LogEvent event = parserEvent(line);

                if(event != null) {
                    report.addEvent(event);
                }

            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }


        }

        report.compute();

    }

    private void initParsers() {
        eventParsers.add(new UnitDiedEventParser());
        eventParsers.add(new SwingEventParser());
        eventParsers.add(new RangeEventParser());
        eventParsers.add(new SpellEventParser(report));

    }

    private LogEvent parserEvent(String line) throws ParseException, java.text.ParseException {
        String[] split = line.split(" +",3);

        if(split.length != 3) {
            throw new ParseException("Bad formated line. Excepted Date Time Other. Found: "+line);
        }

        Timestamp time = new Timestamp(split[0], split[1]);
        String params = split[2];

        String[] splitParam = params.split(",");

        if(splitParam.length < 7) {
            throw new ParseException("Bad formated line. Excepted at least 7. Found: "+params);
        }

        String key = splitParam[0];

        Unit sourceUnit = report.getUnitManager().parseUnit(parseGuid(splitParam[1]), parseString(splitParam[2]), parseLong(splitParam[3]));
        Unit targetUnit = report.getUnitManager().parseUnit(parseGuid(splitParam[4]), parseString(splitParam[5]), parseLong(splitParam[6]));


        for(EventParser eventParser: eventParsers) {
            if(eventParser.match(key)) {
                return eventParser.parse(time, sourceUnit, targetUnit, splitParam);
            }
        }



        throw new ParseException("Unknow event "+key+" in "+ params);

    }

    public static long parseLong(String string) {
        return Long.parseLong(string.substring(2), 16);
    }

    public static int parseInt(String string) {
        return Integer.parseInt(string.substring(2), 16);
    }

    public static String parseString(String string) {
        return string.substring(1, string.length()-1);
    }

    public static String parseGuid(String string) {
        return string.substring(2);
    }

    public static void main(String[] args) throws FileNotFoundException, java.text.ParseException {
        LogParser parser = new LogParser("/home/fred/.wine/drive_c/Program Files/World of Warcraft/Logs/WoWCombatLog.txt");

        LogReport report = parser.getReport();

        System.out.println("Nombre d'evenements: "+report.getEventCount());


        List<Fight> allFights = report.getAllFigths();

        for(Fight fight:allFights) {
            int index = allFights.indexOf(fight);
            List<Unit> mobs = fight.getMobs();

            String mobNames = "";
            for(Unit mob:mobs) {
                mobNames += mob.getName()+", ";
            }

            mobNames = mobNames.substring(0, mobNames.length()-2);


            System.out.println("Combat "+index);
            System.out.println("    Mobs  : "+mobNames);
            System.out.println("    Début : "+fight.getBeginTime().toString());
            System.out.println("    Fin   : "+fight.getEndTime().toString());
            System.out.println("    Durée : "+TimeInterval.print(fight.getTimeInterval().getDuration()));
        }




        Unit homeostasie = report.getUnitManager().getUniqueByName("Homéostasie");
        List<Fight> fights = report.getFigthsWith("Chimaeron");

//        for(Fight fight:fights) {
//
//            System.out.println("");
//            System.out.println("");
//            System.out.println("============");
//            System.out.println("= Chimaron =");
//            System.out.println("============");
//            System.out.println("");
//
//            List<LogEvent> eventList = fight.getEventList();
//            for(LogEvent event: eventList) {
//                if(event.involve(homeostasie)) {
//                    System.out.println(event.toString());
//                }
//            }
//        }




    }

    public LogReport getReport() {
        return report;
    }

}
