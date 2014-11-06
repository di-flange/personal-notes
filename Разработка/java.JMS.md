#### Менеджер полученных сообщений #### 

    public class SerializableJmsDispatcher extends AbstractJmsDispatcher {
        private final Map< Class, Listener> listeners;

        public SerializableJmsDispatcher(Map<Class , Listener> listeners) {
            super ();
            
            this.listeners = Collections.unmodifiableMap(listeners);
        }

        @Override
        public void onMessage(final ObjectMessage message, final Session session) throws JMSException {
            final Serializable content = message.getObject();
            
            if (content == null) {
                this.rollback(content, session);
                return;
            }

            final Listener listener = this.listeners.get(content.getClass());
            if (listener == null) { 
                this.rollback(content, session);
                return;
            }

            ListenResult result = null;

            try {
                result = listener.listen(content);
            } catch(final Exception exp) {
                if (JMSException.class.isAssignableFrom(exp.getClass())) throw (JMSException) exp;
            }  

            if (result == null || result.getStatus() != JmsTransactionStatus.COMMIT) this.commit(content, session);
            else this.rollback(content, session);

            listener.post(content, result);
        }
    }
