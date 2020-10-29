package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {

        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void otetaanLiikaaTavaraaPois() {

        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(12);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void laitetaanLiikaaTavaraa() {

        varasto.lisaaVarastoon(11);

        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusEiVoiOllaAlussaMiinuksella() {

        varasto = new Varasto(-10,0);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void saldoEiVoiOllaAlussaMiinuksella() {

        varasto = new Varasto(10,-10);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusAlussaPositiivinen() {

        varasto = new Varasto(10,10);

        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void saldoAlussaPositiivinen() {

        varasto = new Varasto(10,10);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenMaaranLisaaminenEiToimi() {

        varasto.lisaaVarastoon(-10.0);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenMaaranOttaminenEiToimi() {

        double maara = varasto.otaVarastosta(-10.0);

        assertEquals(0.0, maara, vertailuTarkkuus);
    }

    @Test
    public void tilavuusNollaVaikkaYritettäisiinNegatiivistaMaaraa() {

        varasto = new Varasto(-10);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikein() {

        varasto.lisaaVarastoon(10.0);
        varasto.otaVarastosta(2.2);

        assertEquals("saldo = 7.8, vielä tilaa 2.2", varasto.toString());
    }
}