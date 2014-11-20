Change base url:

        select * 
        from core_config_data 
        where path in ('web/unsecure/base_url', 'web/secure/base_url', 'admin/url/custom');

Restore cart:

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
    
Mark order as paid:

    $order->setStatus($paidStatus)->save();
    
    if ($order->getCanSendNewEmailFlag()) $order->sendNewOrderEmail();

    if ($createInvoice) {
        if(! $order->canInvoice()) Mage::throwException(Mage::helper('core')->__('Cannot create an invoice.'));

            $invoice = Mage::getModel('sales/service_order', $order)->prepareInvoice();

            $invoice->setRequestedCaptureCase(Mage_Sales_Model_Order_Invoice::CAPTURE_ONLINE);
            $invoice->register();

            Mage::getModel('core/resource_transaction')
                ->addObject($invoice)
                ->addObject($invoice->getOrder())
                ->save();
            }
    }
