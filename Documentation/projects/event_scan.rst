==========
Event Scan
==========

It's job is to scan tickets and is used by Event Cinemas for outdoor cinema ticket scanning where they do not have their usual registers.

Project background
==================

Event Scan was an app developed by a Digital Agency that was then given to Black Label Solutions to upgrade it to iOS7 and add a few new features.

It was developed some time ago and the code has some serious issues (e.g. string range plucking instead of XML parsing, custom stream creation format).

Currently, the goal is to clean the code up as we go, add urgently requested features but eventually transition to Storyboards and just lift only the required business logic code from here so the new app is almost a total re-write.
 
Requirements
============

Event Cinemas use 5th generation iPods The Linea-pro barcode scanner. You'll need the scanner connected to test, um, scanning, but you can also use the app without the scanner by typing the barcodes in.

.. note:: The scanner does not work if the device is connected to a computer. This means step debugging is out and debugging with UIAlertView is in. More on debugging a bit later.

Testing the app
===============

.. note:: You'll need to get a selection of barcodes for testing from Event Cinemas. Talk to the product owner who can organise for these to be sent to you.

1. Connect to the VPN
---------------------

In order for the app to talk to the Black Label Solutions backend, you need to connect the phone to the VPN. 

The test device that you'll get should already have the VPN connection configured but below is a screenshot of the settings.

.. image:: /_static/img/event-cinemas/vpn-config.png
    :width: 400px
    :align: center

To connect to the VPN, go to Settings, then tap the **VPN** switch. 

.. image:: /_static/img/event-cinemas/settings.png
    :width: 400px
    :align: center

Authentication details are:

 * username: EXT_Blacklabel
 * Password: bL@ckL@b3L
	 
2. Sign in
----------

Enter in the following details:

 * Server name: 10.254.254.1
 * Kiosk ID: 61.88.2.16
	
.. image:: /_static/img/event-cinemas/config.png	
    :width: 400px
    :align: center

Then tap **Test** which will verify your details and confirm that it could connect to the sever by showing you the contents of a response.

3. Testing
----------

You'll need barcodes to do anything else. 

4. Debugging
------------

There are two preprocessor macro's defined for this project to assist in debugging debug and adhoc versions of the app.

``AHL_UI_DEBUG``: Set this to 1 in order to see UIAlertView's with key content at different parts of the ticket collection process.

``AHL_DONT_COLLECT_TICKETS``: Set this to 1 in order for the UI to **pretend** it successfully received a response from the server that all tickets were collected successfully. This is for when you're testing flow but you don't want to actually process the tickets. Getting test barcodes is difficult for some reason so this comes in handy.

.. important:: DO NOT SET THESE FOR THE RELEASE VERSION!

Partial Collection
==================

The partial collection functionality allows some but not all tickets to be collected for a booking.

.. note:: This work is currently un-finished. The data layer is done but needs more rigourous testing around what error cases need to be handed. The UI layer is still to be done. Please see the ``EventScan Partial Ticket flow`` folder in the ``_static/event-scan`` directory for UI layout details.

.. important:: To enable the partial collection flow, set ``AHL_PARTIAL_COLLECTED_ENABLED`` in the preprocessor macros for the adhoc build.

VSSWebServices
--------------

A new processing block was added to ``postSoapAction:``.  This code is not pretty but it simply tries to follow how the previous soap responses have been processed.


AHLCollectBookingViewController
-------------------------------

The ``BookingDetailViewController`` will (if ``AHL_PARTIAL_COLLECTED_ENABLED`` is defined) push ``AHLCollectBookingViewController`` onto the stack, passing it a reference to the Bookings object which it needs in order to get the booking number.

Staying consistent with how data flow is handled in other sections of the app, the ``AHLCollectBookingViewController`` calls into ``VSSWebServices`` in order to get the inner XML response that has the ticket info.

It then passes this XML response string to the new ``AHLPartialBookingDataManager`` who in turn provides an API for getting booking and individual ticket info.


Third party libraries
---------------------

The below third party libraries take care of turning the entity encoded XML packet into normal XML and then converting this XML into an NSDictionary.

 * Entity decoder - https://github.com/mwaterfall/MWFeedParser/blob/master/Classes/NSString%2BHTML.h
 * XML to NSDictionary convert - https://github.com/nicklockwood/XMLDictionary

Sample GetBookingDataXml reponse
--------------------------------

It's a bit of a monster so here is a sample response with two tickets and two tickets still available (BookingH_dtmDateCollected is empty).

.. code:: xml

  <?xml version="1.0" encoding="UTF-8"?>
  <Vista_Booking_Data>
     <tblBooking_Header>
        <BookingH_intNextBookingNo>44183</BookingH_intNextBookingNo>
        <BookingH_strName>BILL</BookingH_strName>
        <BookingH_strPhone>12345</BookingH_strPhone>
        <BookingH_strPickup>BILL</BookingH_strPickup>
        <BookingH_strComments />
        <BookingH_curAmountPaid>39.0000</BookingH_curAmountPaid>
        <BookingH_intTicketsPrt>0</BookingH_intTicketsPrt>
        <BookingH_strStatus>P</BookingH_strStatus>
        <TransC_lgnNumber>57841</TransC_lgnNumber>
        <BookingH_dtmDateBooked>20141120175721</BookingH_dtmDateBooked>
        <BookingH_dtmDateCollected />
        <BookingH_strRemoteBooking />
        <BookingH_strSource>POSBK</BookingH_strSource>
        <BookingH_intVoucherPrt>0</BookingH_intVoucherPrt>
        <BookingH_strPickupWorkstn />
        <BookingH_intPickupUser>0</BookingH_intPickupUser>
        <BookingH_strCardNo />
        <BookingH_strCardExpiry />
        <BookingH_strCardType />
        <BookingH_strKioskIsPrinting>N</BookingH_strKioskIsPrinting>
        <BookingH_strKioskPrintFail />
        <BookingH_strOtherTablesUpdated />
        <BookingH_strCardSwiped>N</BookingH_strCardSwiped>
        <timestamp />
        <BookingH_dtmLastSessRealShow />
        <BookingH_curValueOfBooking>39.0000</BookingH_curValueOfBooking>
        <BookingH_strPickupTrack />
        <BookingH_strRemotePOSId />
        <BookingH_strRemotePOSLocation />
        <BookingH_strCustomerTicketRef />
        <BookingH_intBookedUser>9998</BookingH_intBookedUser>
        <BookingH_strBookedWorkstn>GTSCINAP01</BookingH_strBookedWorkstn>
        <BookingH_strAutoPay />
        <BookingH_strConfirmedUnpaidBk />
        <BookingH_strBookingId>WRMZRGR</BookingH_strBookingId>
        <BookingH_intFiscalReceiptWWW>0</BookingH_intFiscalReceiptWWW>
        <Cinema_strCode>9999</Cinema_strCode>
        <BookingH_strCardHash />
        <BookingH_strEmail />
        <BookingH_strGroupSalesBookingRef />
        <BookingH_strGroupSalesBookingType />
        <BookingH_dtmDateStart />
     </tblBooking_Header>
     <tblBooking_Detail>
        <Ticket>
           <BookingD_intNextBookingNo>44183</BookingD_intNextBookingNo>
           <BookingD_intSequence>1</BookingD_intSequence>
           <Session_lngSessionId>166135</Session_lngSessionId>
           <Area_bytNum>1</Area_bytNum>
           <PGroup_strCode>0047</PGroup_strCode>
           <Price_strCode>0001</Price_strCode>
           <BookingD_curAmountPaid>0</BookingD_curAmountPaid>
           <BookingD_intTicketsPrt>0</BookingD_intTicketsPrt>
           <BookingD_curValue>1950</BookingD_curValue>
           <BookingD_curSTax>177</BookingD_curSTax>
           <BookingD_intNoOfSeats>1</BookingD_intNoOfSeats>
           <BookingD_Redemption>N</BookingD_Redemption>
           <BookingD_strStatus>P</BookingD_strStatus>
           <ScreenD_strPhyRowId>P</ScreenD_strPhyRowId>
           <ScreenD_strSeatId>15</ScreenD_strSeatId>
           <ScreenD_bytGridRowId>7</ScreenD_bytGridRowId>
           <BookingD_bytGridColId>16</BookingD_bytGridColId>
           <BookingD_curRedempValueEach>0</BookingD_curRedempValueEach>
           <timestamp />
           <AreaCat_strCode>0000000001</AreaCat_strCode>
           <BookingD_curSTax2>0</BookingD_curSTax2>
           <BookingD_curSTax3>0</BookingD_curSTax3>
           <BookingD_curSTax4>0</BookingD_curSTax4>
           <BookingD_strParentTType_strCode />
           <BookingD_intPackageGroupNo>1</BookingD_intPackageGroupNo>
           <BookingD_curDiscount>0</BookingD_curDiscount>
           <BookingD_strRptCode />
           <BookingD_dtmDateCollected />
           <TransT_intSequence>1</TransT_intSequence>
           <BookingD_intSwapSequence>0</BookingD_intSwapSequence>
           <BookingD_curFullPriceEach>1950</BookingD_curFullPriceEach>
           <BookingD_strVoucherBarcode />
           <BookingD_strVoucherCommitted />
           <BookingD_strMemberId />
           <BookingD_intLoyaltyRecogId>0</BookingD_intLoyaltyRecogId>
           <BookingD_strLoyaltyStatus />
           <BookingD_strPickupWorkstn />
           <BookingD_intPickupUser>0</BookingD_intPickupUser>
           <BookingD_strVoucherPIN />
           <BookingD_curTPAValueEach>0</BookingD_curTPAValueEach>
           <TPA_strCode />
           <BookingD_intLoyaltyAdvanceBookingRecogId>0</BookingD_intLoyaltyAdvanceBookingRecogId>
           <BookingD_strAdditionalPrintData />
           <BookingD_strBarcodeMember />
           <BookingD_strBookingVoucher />
           <Session_dtmRealShow>20141120184500</Session_dtmRealShow>
           <Film_strTitle>A MILLION WAYS TO DIE IN THE WEST</Film_strTitle>
           <Film_strTitleAlt />
           <Price_strDescription>Adult</Price_strDescription>
           <Price_strDescriptionAlt />
           <Price_strParentDescription />
           <Price_strParentDescriptionAlt />
           <STax_strCode />
           <Price_strRedemption>N</Price_strRedemption>
           <Price_strComp>N</Price_strComp>
           <TType_strMemberCard>N</TType_strMemberCard>
           <Price_strSalesChannels>^~^KIOSK^~^POS^~^POSBK^~^</Price_strSalesChannels>
           <HOPK>0001</HOPK>
           <Parent_HOPK />
           <Screen_strNum>4</Screen_strNum>
           <Screen_strName>Cinema 4</Screen_strName>
           <CinOperator_strCode>GTSCIN</CinOperator_strCode>
           <Session_Finish>20141120205100</Session_Finish>
           <TransT_strBarcodeMember />
           <TType_strBarcode />
           <Film_strTitleAlt2 />
           <Film_strTitleAlt3 />
           <Price_strDescriptionAlt2 />
           <Price_strDescriptionAlt3 />
           <Screen_strNameAlt>4</Screen_strNameAlt>
           <Screen_strNameAlt2 />
           <Screen_strNameAlt3 />
        </Ticket>
        <Ticket>
           <BookingD_intNextBookingNo>44183</BookingD_intNextBookingNo>
           <BookingD_intSequence>2</BookingD_intSequence>
           <Session_lngSessionId>166135</Session_lngSessionId>
           <Area_bytNum>1</Area_bytNum>
           <PGroup_strCode>0047</PGroup_strCode>
           <Price_strCode>0001</Price_strCode>
           <BookingD_curAmountPaid>0</BookingD_curAmountPaid>
           <BookingD_intTicketsPrt>0</BookingD_intTicketsPrt>
           <BookingD_curValue>1950</BookingD_curValue>
           <BookingD_curSTax>177</BookingD_curSTax>
           <BookingD_intNoOfSeats>1</BookingD_intNoOfSeats>
           <BookingD_Redemption>N</BookingD_Redemption>
           <BookingD_strStatus>P</BookingD_strStatus>
           <ScreenD_strPhyRowId>P</ScreenD_strPhyRowId>
           <ScreenD_strSeatId>14</ScreenD_strSeatId>
           <ScreenD_bytGridRowId>7</ScreenD_bytGridRowId>
           <BookingD_bytGridColId>17</BookingD_bytGridColId>
           <BookingD_curRedempValueEach>0</BookingD_curRedempValueEach>
           <timestamp />
           <AreaCat_strCode>0000000001</AreaCat_strCode>
           <BookingD_curSTax2>0</BookingD_curSTax2>
           <BookingD_curSTax3>0</BookingD_curSTax3>
           <BookingD_curSTax4>0</BookingD_curSTax4>
           <BookingD_strParentTType_strCode />
           <BookingD_intPackageGroupNo>2</BookingD_intPackageGroupNo>
           <BookingD_curDiscount>0</BookingD_curDiscount>
           <BookingD_strRptCode />
           <BookingD_dtmDateCollected />
           <TransT_intSequence>2</TransT_intSequence>
           <BookingD_intSwapSequence>0</BookingD_intSwapSequence>
           <BookingD_curFullPriceEach>1950</BookingD_curFullPriceEach>
           <BookingD_strVoucherBarcode />
           <BookingD_strVoucherCommitted />
           <BookingD_strMemberId />
           <BookingD_intLoyaltyRecogId>0</BookingD_intLoyaltyRecogId>
           <BookingD_strLoyaltyStatus />
           <BookingD_strPickupWorkstn />
           <BookingD_intPickupUser>0</BookingD_intPickupUser>
           <BookingD_strVoucherPIN />
           <BookingD_curTPAValueEach>0</BookingD_curTPAValueEach>
           <TPA_strCode />
           <BookingD_intLoyaltyAdvanceBookingRecogId>0</BookingD_intLoyaltyAdvanceBookingRecogId>
           <BookingD_strAdditionalPrintData />
           <BookingD_strBarcodeMember />
           <BookingD_strBookingVoucher />
           <Session_dtmRealShow>20141120184500</Session_dtmRealShow>
           <Film_strTitle>A MILLION WAYS TO DIE IN THE WEST</Film_strTitle>
           <Film_strTitleAlt />
           <Price_strDescription>Adult</Price_strDescription>
           <Price_strDescriptionAlt />
           <Price_strParentDescription />
           <Price_strParentDescriptionAlt />
           <STax_strCode />
           <Price_strRedemption>N</Price_strRedemption>
           <Price_strComp>N</Price_strComp>
           <TType_strMemberCard>N</TType_strMemberCard>
           <Price_strSalesChannels>^~^KIOSK^~^POS^~^POSBK^~^</Price_strSalesChannels>
           <HOPK>0001</HOPK>
           <Parent_HOPK />
           <Screen_strNum>4</Screen_strNum>
           <Screen_strName>Cinema 4</Screen_strName>
           <CinOperator_strCode>GTSCIN</CinOperator_strCode>
           <Session_Finish>20141120205100</Session_Finish>
           <TransT_strBarcodeMember />
           <TType_strBarcode />
           <Film_strTitleAlt2 />
           <Film_strTitleAlt3 />
           <Price_strDescriptionAlt2 />
           <Price_strDescriptionAlt3 />
           <Screen_strNameAlt>4</Screen_strNameAlt>
           <Screen_strNameAlt2 />
           <Screen_strNameAlt3 />
        </Ticket>
     </tblBooking_Detail>
     <tblBooking_Inv />
  </Vista_Booking_Data>

Resources
=========

Look in the ``_static/event-scan`` directory for documentation resources about the Linea-pro and other stuff.

Application flow
================

Below are images from the iOS 6 build which show ticket collection and recent collection flows.

Collect tickets flow
--------------------

.. image:: /_static/img/event-cinemas/config.png
    :width: 400px
    :align: center
		
.. image:: /_static/img/event-cinemas/flow/tickets_02.png
    :width: 400px
    :align: center	 

.. image:: /_static/img/event-cinemas/flow/tickets_03.png
    :width: 400px
    :align: center	 

.. image:: /_static/img/event-cinemas/flow/tickets_04.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/tickets_05.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/tickets_06.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/tickets_07.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/tickets_08.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/tickets_09.png
    :width: 400px
    :align: center	 
		
Recent collections flow
-----------------------

Upon ticket completion, you'll be taken to the home screen where the last tickets collected will be displayed.

.. image:: /_static/img/event-cinemas/flow/recent_01.png
    :width: 400px
    :align: center	 

.. image:: /_static/img/event-cinemas/flow/recent_02.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/recent_03.png
    :width: 400px
    :align: center	 
		
.. image:: /_static/img/event-cinemas/flow/recent_04.png
    :width: 400px
    :align: center	 
		
		
		
		
		
		
		

