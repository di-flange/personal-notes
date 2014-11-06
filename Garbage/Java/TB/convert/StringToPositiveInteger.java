package ee.jiss.resourceflow.utils.convert;

import ee.jiss.resourceflow.utils.exception.reaction.TolerateReaction;
import ee.jiss.resourceflow.utils.fop.Function;

@SuppressWarnings("UnusedDeclaration")
public class StringToPositiveInteger implements Converter<String, Integer> {

    private static final Function<Exception, Integer> TOLERATE_REACTION = new TolerateReaction<>();
    public static final StringToPositiveInteger TOLERANT_INSTANCE = new StringToPositiveInteger();

    private final Function<Exception, Integer> formatReaction;
    private final Function<Exception, Integer> nullReaction;
    private final Function<Exception, Integer> negativeReaction;
    private final Function<Exception, Integer> zeroReaction;

    public StringToPositiveInteger() {

        this.formatReaction = TOLERATE_REACTION;
        this.nullReaction = TOLERATE_REACTION;
        this.negativeReaction = TOLERATE_REACTION;
        this.zeroReaction = TOLERATE_REACTION;
    }

    public StringToPositiveInteger(final Function<Exception, Integer> formatReaction,
                                   final Function<Exception, Integer> nullReaction,
                                   final Function<Exception, Integer> negativeReaction,
                                   final Function<Exception, Integer> zeroReaction) {
        
        this.formatReaction = formatReaction == null ? TOLERATE_REACTION : formatReaction;
        this.nullReaction = nullReaction == null ? TOLERATE_REACTION : nullReaction;
        this.negativeReaction = negativeReaction == null ? TOLERATE_REACTION : negativeReaction;
        this.zeroReaction = zeroReaction == null ? TOLERATE_REACTION : zeroReaction;
    }

    @Override
    public Integer execute(final String in) {

        if (in == null) {
            return this.nullReaction.execute(new NullPointerException());
        }

        final Integer value;

        try {
            value = Integer.valueOf(in);
        } catch (final NumberFormatException exp) {
            return this.formatReaction.execute(exp);
        }

        if (value < 0) {
            return this.negativeReaction.execute(new NumberFormatException());
        }

        if (value.equals(0)) {
            return this.zeroReaction.execute(new NumberFormatException());
        }

        return value;
    }
}