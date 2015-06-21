/*
 * Copyright (c) 2015. Tammo Schwindt (Tammon) <dev@tammon.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.tammon.dev.mdc.server.DAO;

import de.tammon.dev.mdc.server.model.SimpleProductionParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tammschw on 19/06/15.
 */
@Component
public class HydraDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    @Autowired
    RestTemplate restTemplate;

    String listType, portNumber, hostName, usrNumber;

    public List<SimpleProductionParameter> getSimpleProductionParametersByOrderId(String id) {
        String dataList;
        if ((dataList = getListByOrderId(id)) == null) return null;

//        String dataList = "ROW.IDX=ROWINDEX|ANR=Auftragsnummer gesammt|AUNR=Auftragsnummer|AGNR=AG-Nummer|AFOLG=Folge|UAGNR=Unter-AG Nummer|SPLNR=Splitt-Nummer|AUART=Auftragsart|MNR=Geplante Einzelmaschine|POS=Anzeigeposition|MGRP=Maschinengruppe|MDE_MASCH=MDE-Verarbeitung der Maschine|ATK=Artikel|ATKBEZ=Artikelbezeichnung|AST=Auftragstatus|ASTTXT=Statustext|EGR:BMK01=Gebuchte Leistung in BMK 01|EGR:BMK02=Gebuchte Leistung in BMK 02|EGR:BMK03=Gebuchte Leistung in BMK 03|EGR:BMK04=Gebuchte Leistung in BMK 04|EGR:BMK05=Gebuchte Leistung in BMK 05|EGR:BMK06=Gebuchte Leistung in BMK 06|EGR:BMK07=Gebuchte Leistung in BMK 07|EGR:BMK08=Gebuchte Leistung in BMK 08|EGR:BMK09=Gebuchte Leistung in BMK 09|EGR:BMK10=Gebuchte Leistung in BMK 10|EGR:BMK11=Gebuchte Leistung in BMK 11|EGR:BMK12=Gebuchte Leistung in BMK 12|ANR:MENGEPROZ_UEBLI=Überlieferung|ANR:OPT_UEBLI=Reaktion auf Überlieferung|ANR:MENGEPROZ_UNTLI=Unterlieferung|ANR:OPT_UNTLI=Reaktion auf Unterlieferung|EGS:GUT=seit Anmeldung|SGE:B=Basismengeneinheit|SGE:P=Prim. Erfassungsmengeneinheit|SGE:S=Sek. Erfassungsmengeneinheit|SGE:T=Tert. Erfassungsmengeneinheit|SGR:AUSB=Sollausschuss|SGR:AUSP=Sollausschuss (Prim.)|SGR:AUSS=Sollausschuss (Sek.)|SGR:AUST=Sollausschuss (Tert.)|SGR:GUTB=Sollmenge (Basismengeneinheit)|SGR:GUTP=Sollmenge (Prim.)|SGR:GUTS=Sollmenge (Sek.)|SGR:GUTT=Sollmenge (Tert.)|EGR:GUTB=Gutmenge (Basismengeneinheit)|EGR:GUTP=Gutmenge (Prim.)|EGR:GUTS=Gutmenge (Sek.)|EGR:GUTT=Gutmenge (Ter.)|EGR:AUSB=Ausschussmenge (Basismengeneinheit)|EGR:AUSP=Ausschussmenge (Prim.)|EGR:AUSS=Ausschussmenge (Sek.)|EGR:AUST=Ausschussmenge (Tert.)|EGR:NCHB=Nacharbeitsmenge|EGR:NCHP=Nacharbeitsmenge (Prim.)|EGR:NCHS=Nacharbeitsmenge (Sek.)|EGR:NCHT=Nacharbeitsmenge (Tert.)|EGR:PRBB=Problemmenge|EGR:PRBP=Problemmenge (Prim.)|EGR:PRBS=Problemmenge (Sek.)|EGR:PRBT=Problemmenge (Tert.)|AGE:B=Mengeneinheit|AGE:P=Mengeneinheit (Prim.)|AGE:S=Mengeneinheit (Sek.)|AGE:T=Mengeneinheit (Tert.)|TLG=Teiligkeit|SZY=Sollzyklus|AGBEZ=Bezeichnung des Arbeitsgangs|ASTV=VStatus|AGMFKZ=parallele Fertigung|CHPFL=chargenpflichtig|APRIO=externe Priorität|FIX=fixiert|DSBEZ=Datensatzbezeichner für ALS|OPT:SNR=Serialnummernpflicht|ANR_BEARBZ=Bearbeitungs-Zeit|ANR_ANDATB=Datum An.|ANR_ANZEIB=Zeit An.|AST_OPT_PKENN=Produktionskennzeichen|AKTIV=aktiv|VERARBCODE_PLAUS_MENGE=100% Mengenprüfung|EGR:DAUER=Dauer|EGR:PDAUER=Pers.einsatz|EGI:GUT=Gut|EGI:AUS=Ausschuss|BEM1=Bemerkung1|BEM2=Bemerkung2|PANZ=Anzahl Personen|MST=Maschinenstatus|HZTYP=Halbzeugtyp|HZBEZ=Halbzeugbez.|TECHINFO=Techn.Info|TPE=TPEinh. [Vorg.]|CNR=Losnummer|DLL=Kd.-Losnr|DLLKZ=Kd.-Loskennz|LOSGR=Losgroesse|HZTYP:EINH=Einheit|PLAUS:MATOK=Plaus. MatOK|MANR=RF: Mutterarbeitsgang|RF:AGTYP=RF: Typ des Arbeitsgangs|LAYOUT=Etikettenlayout|OPT_PLAN=Eingeplant|WZNR=Komponentenliste Werkzeug|WNR_TLG=Werkzeug Teiligkeit|OPT_INFOAN=Kennzeichen Notizen vorhanden|OPT_INFOAI=Kennzeichen Langtext vorhanden|AUART_OPT_VISAAN=Anzeige Auftragsinfo nach Anmeldung|ANR_IMPFAKT=Soll-Impulsfaktor|AUNR_KBN_LBEZID=Regelkreis-Lieferbeziehung ID|PRUEFSTAT:BEZK=|PRUEFSTAT:BEZL=|PRUEFSTAT:ZEIT=|PRUEFSTAT:FARBE=| 1|400000080010|40000008|0010||||0|FESTO001|0|FESTO001||TASCHENLAMPE|Taschenlampe|E|beendet|0|0|0|0|0|0|0|0|0|0|45|0|100.000000| |100.000000| |0.000000|ST|ST|||0.000000|0.000000|0.000000|0.000000|1.000000|1.000000|0.000000|0.000000|1.000000|1.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|0.000000|ST|ST|||1.000000|0.000000|Auslagern|E|N|N|0|N||N|0|06/17/2015|57068|E||NNNN|45|0|0|0|||0|0|SYSTEM|||||||0|||400000080010|x||M||0||| |.000000||||||";

        List<SimpleProductionParameter> simpleProductionParameters = new ArrayList<>();

        String[] headerValueSplitter = dataList.split("(?<=PRUEFSTAT:FARBE=\\|)");

        String[] header = headerValueSplitter[0].split("\\|");
        String[] values = headerValueSplitter[1].split("\\|", -1);

        try {
            for (int i = 0; i < values.length; i++) {
                SimpleProductionParameter simpleProductionParameter = new SimpleProductionParameter();
                simpleProductionParameter.setAttributeName((header[i].split("="))[0].trim());
                simpleProductionParameter.setName((header[i].split("="))[1].trim());
                simpleProductionParameter.setValue(values[i].trim());

                if (checkIsNumericAndIsNotEmpty(values[i])) simpleProductionParameters.add(simpleProductionParameter);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e.toString());
        }

        return simpleProductionParameters;
    }

    private String getListByOrderId(String id) {
        try {
            return restTemplate.getForObject(
                    "http://" + hostName + ":" + portNumber + "/api/?dlg={DLG}",
                    String.class,
                    "LIST;" + listType + "|DAT=today|ZEI=now|USR=" + usrNumber + "|MOD=A|ANR=" + id);
        } catch (ResourceAccessException e) {
            logger.error(e.toString());
            return null;
        }
    }

    private boolean checkIsNumericAndIsNotEmpty(String value) {
        double d;
        try {
            d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return !value.isEmpty();
        }
        return (d != 0);
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUsrNumber() {
        return usrNumber;
    }

    public void setUsrNumber(String usrNumber) {
        this.usrNumber = usrNumber;
    }
}
