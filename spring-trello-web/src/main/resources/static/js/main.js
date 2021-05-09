flatpickr.localize(flatpickr.l10ns.ru);
(function ($) {

    $(".date-input").flatpickr({
        minDate: new Date(),
        dateFormat: 'Y-m-d',
    })

})(jQuery);