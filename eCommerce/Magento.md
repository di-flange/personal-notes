Magento restore cart:

    private function restoreShoppingCart($order) {

        if (! $order->canCancel()) return false;

        $quote = Mage::getModel('sales/quote')->load($order->getQuoteId());
        $quote->setIsActive(true)->save();

        $order->cancel();
        $order->setStatus('canceled');
        $order->save();

        Mage::getSingleton('core/session')->setFailureMsg('order_failed');
        Mage::getSingleton('checkout/session')->setFirstTimeChk('0');

        return true;
    }