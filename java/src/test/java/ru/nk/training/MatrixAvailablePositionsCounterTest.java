package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nk.training.MatrixAvailablePositionsCounter.RowInterval;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixAvailablePositionsCounterTest {
    private MatrixAvailablePositionsCounter counter;

    @BeforeEach
    public void setUp() {
        counter = new MatrixAvailablePositionsCounter();
    }

    @Test
    public void returnsNumberOfMatrixElementsWhenNothingIsOccupied() {
        assertEquals(50, counter.count(5, 10, new ArrayList<>()));
    }

    @Test
    public void throwsWhenOccupiedIntervalsAreNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> counter.count(5, 10, null)
        );
    }

    @Test
    public void throwsWhenNumberOfRowsIsLessThanZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> counter.count(-1, 10, new ArrayList<>())
        );
    }

    @Test
    public void throwsWhenNumberOfColumnsIsLessThanZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> counter.count(5, -1, new ArrayList<>())
        );
    }

    @Test
    public void returnsNumberOfMatrixElementsWithoutOneWhenSingleCellIsOccupied() {
        assertEquals(49, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 3, 3));
            }
        }));
    }

    @Test
    public void returnsNumberOfMatrixElementsWithoutOneRowWhenWholeRowIsOccupied() {
        assertEquals(40, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 0, 9));
            }
        }));
    }

    @Test
    public void returnsNumberAvailablePositionsWhenRowIntervalContainsOthers() {
        assertEquals(40, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 0, 9));
                add(new RowInterval(2, 1, 8));
                add(new RowInterval(2, 2, 7));
            }
        }));
    }

    @Test
    public void returnsNumberAvailablePositionsWhenRowIntervalsAreNearEachOther() {
        assertEquals(43, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 1, 4));
                add(new RowInterval(2, 5, 7));
            }
        }));
    }

    @Test
    public void returnsNumberAvailablePositionsWhenRowIntervalsIntersect() {
        assertEquals(42, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 1, 4));
                add(new RowInterval(2, 6, 8));
                add(new RowInterval(2, 3, 6));
            }
        }));
    }

    @Test
    public void returnsNumberAvailablePositionsWhenRowIntervalsOverlaps() {
        assertEquals(42, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 1, 4));
                add(new RowInterval(2, 4, 8));
            }
        }));
    }

    @Test
    public void returnsNumberAvailablePositionsWhenRowIntervalOverridesOthers() {
        assertEquals(93, counter.count(5, 20, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 10, 14));
                add(new RowInterval(2, 10, 16));
                add(new RowInterval(2, 10, 15));
            }
        }));
    }

    @Test
    public void returnsNumberAvailablePositionsWhenIntervalsOnDifferentRows() {
        assertEquals(43, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(2, 1, 4));
                add(new RowInterval(3, 6, 8));
            }
        }));
    }

    @Test
    public void returnsZeroWhenAllRowsAreOccupied() {
        assertEquals(0, counter.count(5, 10, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(0, 0, 9));
                add(new RowInterval(1, 0, 9));
                add(new RowInterval(2, 0, 9));
                add(new RowInterval(3, 0, 9));
                add(new RowInterval(4, 0, 9));
            }
        }));
    }

    @Test
    public void returnsNumberOfAvailablePositionsForLargeMatrix() {
        assertEquals(335820405811182700L, counter.count(693177850, 484465003, new ArrayList<RowInterval>() {
            {
                add(new RowInterval(236503425, 316332185, 461015094));
                add(new RowInterval(325185142, 159242614, 308431801));
                add(new RowInterval(55252985, 91207425, 145729634));
                add(new RowInterval(570145737, 66187475, 391212599));
                add(new RowInterval(371200914, 365371881, 394702623));
                add(new RowInterval(118881008, 192274799, 210407481));
                add(new RowInterval(494441152, 367388413, 454335995));
                add(new RowInterval(679306823, 25218435, 93249071));
                add(new RowInterval(664325322, 205159569, 231255972));
                add(new RowInterval(397092492, 153491683, 180964463));
                add(new RowInterval(481315380, 2248119, 93447774));
                add(new RowInterval(479915593, 29751179, 336039383));
                add(new RowInterval(237043891, 220540563, 426154492));
                add(new RowInterval(350500882, 222092049, 333611679));
                add(new RowInterval(467416229, 12887610, 289492619));
                add(new RowInterval(118881008, 192274799, 199091059));
                add(new RowInterval(55252985, 145729634, 180957023));
                add(new RowInterval(236503425, 461015094, 483469099));
                add(new RowInterval(467416229, 289492619, 406503092));
                add(new RowInterval(664325322, 205159569, 413796562));
                add(new RowInterval(467416229, 12887610, 163385051));
                add(new RowInterval(325185142, 308431801, 378788220));
                add(new RowInterval(679306823, 25218435, 138282969));
                add(new RowInterval(55252985, 145729634, 180435001));
                add(new RowInterval(236503425, 461015094, 465216924));
                add(new RowInterval(397092492, 153491683, 438933078));
                add(new RowInterval(570145737, 66187475, 357191180));
                add(new RowInterval(664325322, 231255972, 378071075));
                add(new RowInterval(325185142, 308431801, 393452445));
                add(new RowInterval(371200914, 394702623, 478996773));
                add(new RowInterval(479915593, 336039383, 347196319));
                add(new RowInterval(494441152, 454335995, 462823116));
                add(new RowInterval(397092492, 153491683, 278739319));
                add(new RowInterval(397092492, 180964463, 420999695));
                add(new RowInterval(237043891, 426154492, 445271379));
                add(new RowInterval(570145737, 391212599, 455384598));
                add(new RowInterval(350500882, 222092049, 480223825));
                add(new RowInterval(237043891, 220540563, 416563765));
                add(new RowInterval(570145737, 66187475, 276585243));
                add(new RowInterval(118881008, 210407481, 391001533));
                add(new RowInterval(494441152, 367388413, 455636019));
                add(new RowInterval(467416229, 289492619, 409024637));
                add(new RowInterval(55252985, 145729634, 151698139));
                add(new RowInterval(479915593, 336039383, 365916019));
                add(new RowInterval(118881008, 192274799, 440912975));
                add(new RowInterval(481315380, 93447774, 210550336));
            }
        }));
    }
}
