/**
 * validation message를 화면에 나타낸다.
 * @param data
 */
function renderValidationMessage(data) {
    if (data.responseJSON?.code == "INVALID_INPUT_VALUE") {
        const errors = data.responseJSON.errors
        errors.forEach(error => {
            const {field, defaultMessage} = error;
            let $errorField = $(`[error-field="${field}"`);
            if (!$errorField?.length) {
                $errorField = $(`#${field}`);
            }
            if ($errorField.length) {
                $errorField.after(
                    $('<div/>', {
                        "class": "error-field-text",
                        text: defaultMessage,
                    })
                );
                $errorField.addClass('error-field-area');
            } else {
                console.error(`error field ${field} is not defined`);
            }

        })
    }
}

function removeValidationMessage() {
    $('.error-field-area').removeClass('error-field-area');
    $('.error-field-text').remove();
}

function fileRequest({
                         url = '',
                         method = 'GET',
                         data = new FormData(),
                         validation = true,
                         success = (data) => {
                         },
                         error = (...args) => {
                         },
                     }) {
    removeValidationMessage();
    $.ajax(url, {
        data: data,
        type: method,
        contentType: false,
        processData: false,
        success: (data) => {
            success(data);
        }, error: (data, status) => {
            console.error(data);
            if (validation) {
                renderValidationMessage(data);
            }
            error(data, status);
        }
    })
}

function bodyRequest({
                         url = '',
                         method = 'GET',
                         data = {},
                         contentType = "application/json",
                         validation = true,
                         async = true,
                         success = (data) => {
                         },
                         error = (...args) => {
                         },
                     }) {
    removeValidationMessage();
    return $.ajax(url, {
        async: async,
        type: method,
        contentType: contentType,
        data: JSON.stringify(data),
        success: (data) => {
            success(data);
        }, error: (data, status) => {
            console.error(data);
            if (validation) {
                renderValidationMessage(data);
            }
            error(data, status);
        }
    })
}

/**
 * 현재 위치한 uri의 param name에 해당하는 값을 가져온다.
 * @param parameterName
 * @return {string|null}
 */
function getParameterValue(parameterName) {
    var queryString = window.location.search;
    if (queryString) {
        queryString = queryString.substring(1);
        var params = queryString.split('&');
        for (var i = 0; i < params.length; i++) {
            var param = params[i].split('=');
            var paramName = decodeURIComponent(param[0]);
            var paramValue = decodeURIComponent(param[1]);
            if (paramName === parameterName) {
                return paramValue;
            }
        }
    }
    return null;
}


/**
 * date포맷을 yyyy-mm-dd hh:mm:ss로 변경
 * @param dateTimeString
 * @return {string}
 */
function formatDateTime(dateTimeString) {
    const date = new Date(dateTimeString);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    const formattedDateTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

    return formattedDateTime;
}

/**
 * 숫자에 컴마를 표시한다.
 * @param number
 * @return {string}
 */
function numberFormat(number) {
    if (number == null) return '';
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/**
 * 4글자 마다 -로 구분된 문자열을 반환한다.
 * @param number
 * @return {string}
 */
function serialFormat(number) {
    var str = number.toString();
    var result = '';
    for (var i = 0; i < str.length; i++) {
        if (i > 0 && i % 4 === 0) {
            result += '-';
        }
        result += str.charAt(i);
    }
    return result;
}