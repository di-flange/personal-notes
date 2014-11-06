package ee.jiss.resourceflow.utils.convert;

import ee.jiss.resourceflow.utils.exception.reaction.TolerateReaction;
import ee.jiss.resourceflow.utils.fop.Function;

@SuppressWarnings("UnusedDeclaration")
public class StringToPositiveLong implements Converter<String, Long> {

    private static final Function<Exception, Long> TOLERATE_REACTION = new TolerateReaction<>();
    public static final StringToPositiveLong TOLERANT_INSTANCE = new StringToPositiveLong();

    private final Function<Exception, Long> formatReaction;
    private final Function<Exception, Long> nullReaction;
    private final Function<Exception, Long> negativeReaction;
    private final Function<Exception, Long> zeroReaction;

    public StringToPositiveLong() {

        this.formatReaction = TOLERATE_REACTION;
        this.nullReaction = TOLERATE_REACTION;
        this.negativeReaction = TOLERATE_REACTION;
        this.zeroReaction = TOLERATE_REACTION;
    }

    public StringToPositiveLong(final Function<Exception, Long> formatReaction,
                                final Function<Exception, Long> nullReaction,
                                final Function<Exception, Long> negativeReaction,
                                final Function<Exception, Long> zeroReaction) {
        
        this.formatReaction = formatReaction == null ? TOLERATE_REACTION : formatReaction;
        this.nullReaction = nullReaction == null ? TOLERATE_REACTION : nullReaction;
        this.negativeReaction = negativeReaction == null ? TOLERATE_REACTION : negativeReaction;
        this.zeroReaction = zeroReaction == null ? TOLERATE_REACTION : zeroReaction;
    }

    @Override
    public Long execute(final String in) {

        if (in == null) {
            return this.nullReaction.execute(new NullPointerException());
        }

        final Long value;

        try {
            value = Long.valueOf(in);
        } catch (final NumberFormatException exp) {
            return this.formatReaction.execute(exp);
        }

        if (value < 0L) {
            return this.negativeReaction.execute(new NumberFormatException());
        }

        if (value.equals(0L)) {
            return this.zeroReaction.execute(new NumberFormatException());
        }

        return value;
    }
}