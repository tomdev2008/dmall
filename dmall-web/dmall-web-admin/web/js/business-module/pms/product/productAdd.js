layui.use(['form', 'crud', 'dtree'], function () {
    var $ = layui.jquery,
        form = layui.form,
        dtree = layui.dtree,
        crud = layui.crud;

    var parentIndex = layer.index;
    // 属性列表
    var allAttributeArr = [];
    // 属性类别列表
    var allAttributeTypeArr = [];
    // 添加的规格列表
    var specificationsArr = [];
    // 添加的卖点列表
    var salePointArr = [];
    // 添加的参数列表
    var paramArr = [];

    var body = $("body");

    // 初始化页面
    init();

    /**
     * 页面初始化函数
     */
    function init() {
        crud.initSelect('unit');
        crud.selectTree('addCategory', pmsUrl + '/category/tree/0/4', 'all');
        crud.initSelect('brand');
    }

    /**
     * 商品分类选中事件
     */
    dtree.on("chooseDone('addCategory')", function (obj) {
        var arr = [];
        for (var i = 0; i < obj.checkbarParams.length; i++) {
            arr.push(obj.checkbarParams[i].nodeId);
        }
        var brandObj = {
            "categoryIds": arr
        }
        // 给品牌赋值
        crud.post(pmsUrl + '/brand/list', brandObj, function (response) {
            for (var i = 0; i < response.data.length; i++) {
                $("#brand").append("<option value=" + response.data[i].id + ">" + response.data[i].name + "</option>");
            }
            form.render();
        }, true);
        var attributeObj = {
            "threeCategoryIds": arr
        }
        // 取属性列表
        crud.post(pmsUrl + '/attribute/list', attributeObj, function (response) {
            allAttributeArr = response.data;
            var specification = {
                'attributeId': '',
                'attributeValues': [],
                'input': ''
            };
            specificationsArr = [];
            specificationsArr.push(specification);
            var salePoint = {
                'attributeId': '',
                'attributeValues': [],
                'input': ''
            };
            salePointArr = [];
            salePointArr.push(salePoint);
            initAttribute('specifications');
            initAttribute('salePoint');
        }, true);

        var attributeTypeObj = {
            "categoryIds": arr
        }
        // 取属性类别列表
        crud.post(pmsUrl + '/attributeType/list', attributeTypeObj, function (response) {
            allAttributeTypeArr = response.data;
            var attributeType = {
                'attributeTypeId': '',
                'attributeId': '',
                'attributeValues': [],
                'input':''
            };
            paramArr = [];
            paramArr.push(attributeType);
            initParam(paramArr, allAttributeTypeArr, allAttributeArr);
        }, true);
    });

    /**
     * 初始化参数
     */
    function initParam() {
        var attributeTypeHtml = '';
        for (var i = 0; i < paramArr.length; i++) {
            attributeTypeHtml += '<div id=param_' + i + ' index ="' + i + '" type="param" class="layui-form-item"><div class="layui-inline">';
            var attrType = paramArr[i];
            attributeTypeHtml += '<label class="layui-form-label">选择属性类别</label>';
            attributeTypeHtml += '<div class="layui-input-inline"><select  lay-search="" lay-filter="attributeType"><option value=""></option>';
            var selected = null;
            for (var j = 0; j < allAttributeTypeArr.length; j++) {
                if (attrType.attributeTypeId && attrType.attributeTypeId === allAttributeTypeArr[j].id) {
                    selected = allAttributeTypeArr[j];
                    attributeTypeHtml += '<option selected value="' + allAttributeTypeArr[j].id + '">' + allAttributeTypeArr[j].showName + '</option>';
                } else {
                    attributeTypeHtml += '<option  value="' + allAttributeTypeArr[j].id + '">' + allAttributeTypeArr[j].showName + '</option>';
                }
            }
            attributeTypeHtml += '</select></div>';
            attributeTypeHtml += getAttributeHtml(paramArr);
        }
        $("#param").html('').append(attributeTypeHtml);
        form.render();
    }

    /**
     * 初始化属性
     */
    function initAttribute(type) {
        var attributeHtml = '';
        if (type === 'specifications') {
            attributeHtml = getAttributeHtml(specificationsArr, type);
        } else if (type === 'salePoint') {
            attributeHtml = getAttributeHtml(salePointArr, type);
        }
        $("#" + type).html('').append(attributeHtml);
        form.render();
    }

    /**
     * 获取属性html
     */
    function getAttributeHtml(arr, type) {
        var attributeHtml = '';
        for (var i = 0; i < arr.length; i++) {
            if (type) {
                attributeHtml += '<div id="' + type + "_" + i + '" index="' + i + '" type="' + type + '" class="layui-form-item">'
                    + '<div class="layui-inline">';
            }
            var attribute = arr[i];
            attributeHtml += '<label class="layui-form-label">选择属性</label>';
            attributeHtml += '<div class="layui-input-inline"><select  lay-search="" lay-filter="attribute"><option value=""></option>';
            var selected = null;
            for (var j = 0; j < allAttributeArr.length; j++) {
                if (attribute.attributeId !== '' && attribute.attributeId === allAttributeArr[j].id) {
                    selected = allAttributeArr[j];
                    attributeHtml += '<option selected value="' + allAttributeArr[j].id + '">' + allAttributeArr[j].showName + '</option>';
                } else {
                    attributeHtml += '<option  value="' + allAttributeArr[j].id + '">' + allAttributeArr[j].showName + '</option>';
                }
            }
            attributeHtml += '</select></div>';
            if (selected) {
                attributeHtml = getAttributeValueHtml(selected, attribute, attributeHtml);
            }
            attributeHtml += '&nbsp;<button type="button" class="layui-btn layui-btn-primary add">+</button>&nbsp;';
            if (arr.length !== 1) {
                attributeHtml += '<a type="button" class="layui-btn layui-btn-primary sub">-</a>';
            }
            attributeHtml += '</div></div><br/>';
        }
        return attributeHtml;

    }

    /**
     * 获取属性值html
     */
    function getAttributeValueHtml(attribute, attr, attributeHtml) {
        if (attribute.inputType === 2 && attribute.inputList) {
            // 从列表录入
            attributeHtml += '<label class="layui-form-label">选择属性值</label><div class="layui-input-inline">';
            var inputList = attribute.inputList;
            if (inputList) {
                var inputArr = inputList.split(",");
                for (var k = 0; k < inputArr.length; k++) {
                    var inputArrElement = inputArr[k];
                    if (crud.contains(attr.attributeValues, inputArrElement)) {
                        attributeHtml += '<input type="checkbox" checked  value="' + inputArrElement + '" lay-skin="primary"  title="' + inputArrElement + '" >';
                    } else {
                        attributeHtml += '<input type="checkbox" value="' + inputArrElement + '" lay-skin="primary"  title="' + inputArrElement + '" >';
                    }
                }
            }
            attributeHtml += '</div>';
            if (attribute.handAddStatus === 'Y') {
                attributeHtml += '<label class="layui-form-label">新增属性值</label>';
                if (attr.input) {
                    attributeHtml += '<div class="layui-input-inline"><textarea  name="attributeValue" class="layui-textarea"  placeholder="多个用|隔开,按回车生效">' + attr.input + '</textarea></div>';
                } else {
                    attributeHtml += '<div class="layui-input-inline"><textarea  name="attributeValue" class="layui-textarea" placeholder="多个用|隔开,按回车生效"></textarea></div>';
                }
            }
        } else {
            // 手工录入
            attributeHtml += '<label class="layui-form-label">新增属性值</label>';
            if (attr.input) {
                attributeHtml += '<div class="layui-input-inline"><textarea  name="attributeValue" class="layui-textarea"  placeholder="多个用|隔开,按回车生效">' + attr.input + '</textarea></div>';
            } else {
                attributeHtml += '<div class="layui-input-inline"><textarea  name="attributeValue" class="layui-textarea" placeholder="多个用|隔开,按回车生效"></textarea></div>';
            }
        }
        return attributeHtml;
    }

    /**
     * 监听属性选择事件
     */
    form.on('select(attribute)', function (data) {
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        var type = $(data.elem).parent().parent().parent().attr("type");
        if (type === 'specifications') {
            selectAttr(specificationsArr, parentId, data);
            initAttribute(type);
        } else if (type === 'salePoint') {
            selectAttr(salePointArr, parentId, data);
            initAttribute(type);
        } else {
            for (var i = 0; i < paramArr.length; i++) {
                if (i === parseInt(parentId)) {
                    paramArr[i].attributeId = data.value;
                    paramArr[i].attributeValues = [];
                    paramArr[i].input = '';
                }
            }
            initParam();
        }
    });

    /**
     * 为属性赋值
     */
    function selectAttr(arr, parentId, data) {
        for (var i = 0; i < arr.length; i++) {
            if (i === parseInt(parentId)) {
                arr[i].attributeId = data.value;
                arr[i].attributeValues = [];
                arr[i].input = '';
            }
        }
    }

    /**
     * 新增事件
     */
    body.on('click', '.add', function () {
        var parentId = $(this).parent().parent().attr("index");
        var type = $(this).parent().parent().attr("type");
        if (type === 'specifications') {
            add(specificationsArr, parentId, type);
        } else if (type === 'salePoint') {
            add(salePointArr, parentId, type);
        } else {
            add(paramArr, parentId, type);
        }
    });

    /**
     * 新增属性并刷新元素
     */
    function add(arr, parentId, type) {
        if (type === 'param') {
            var attributeType = {
                'attributeTypeId': '',
                'attributeId': '',
                'attributeValues': [],
                'input': ''
            };
            splice(arr, attributeType, parentId);
            initParam();
        } else {
            var attribute = {
                'attributeId': '',
                'attributeValues': [],
                'input': ''
            }
            splice(arr, attribute, parentId);
            initAttribute(type);
        }
    }

    /**
     * 向数组插入数据
     */
    function splice(arr, obj, parentId) {
        for (var j = 0; j < arr.length; j++) {
            if (j === parseInt(parentId)) {
                arr.splice(j + 1, 0, obj);
            }
        }
    }

    /**
     * 减少事件
     */
    body.on('click', '.sub', function () {
        var parentId = $(this).parent().parent().attr("index");
        var type = $(this).parent().parent().attr("type");
        if (type === 'specifications') {
            specificationsArr = specificationsArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initAttribute(type);
        } else if (type === 'salePoint') {
            salePointArr = salePointArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initAttribute(type);
        } else {
            paramArr = paramArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initParam();
        }
    });

    /**
     * 监听属性类别选择事件
     */
    form.on('select(attributeType)', function (data) {
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        for (var i = 0; i < paramArr.length; i++) {
            if (i === parseInt(parentId)) {
                paramArr[i].attributeTypeId = data.value;
            }
        }
        initParam();
    });

    /**
     * 监听复选框选中事件
     */
    form.on('checkbox', function (data) {
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        var type = $(data.elem).parent().parent().parent().attr("type");
        var arr = [];
        $('#' + type + "_" + parentId + ' input[type=checkbox]:checked').each(function () {
            arr.push($(this).val());
        });
        if (type === 'specifications') {
            setAttributeValues(specificationsArr, arr, parentId);
        } else if (type === 'salePoint') {
            setAttributeValues(salePointArr, arr, parentId);
        } else {
            setAttributeValues(paramArr, arr, parentId);
        }
    });

    /**
     * 为属性值赋值
     */
    function setAttributeValues(attrArr, arr, parentId) {
        for (var i = 0; i < attrArr.length; i++) {
            if (i === parseInt(parentId)) {
                attrArr[i].attributeValues = arr;
            }
        }
    }

    /**
     * 绑定新增属性值的回车事件
     */
    $(document).bind('keypress', "input[name=attributeValue]", function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            console.log(event.target.value);
            var index = $(event.target).parent().parent().parent().attr("index");
            var type = $(event.target).parent().parent().parent().attr("type");
            if (type === 'specifications') {
                setInput(specificationsArr, event.target.value, index);
            } else if (type === 'salePoint') {
                setInput(salePointArr, event.target.value, index);
            } else {
                setInput(paramArr, event.target.value, index);
            }
        }
    });

    function setInput(attrArr, input, parentId) {
        for (var i = 0; i < attrArr.length; i++) {
            if (i === parseInt(parentId)) {
                attrArr[i].input = input;
            }
        }
    }

    // 提交数据
    $("#submit").bind('click', function () {

        // 基本信息
        var basicProduct = {
            'name': $("#name").val(),
            'description': $("#description").val(),
            'unit': $("#unit").val(),
            'weight': $("#weight").val()
        }

        specificationsArr = specificationsArr.filter(function (element) {
            return element.attributeId;
        });
        salePointArr = salePointArr.filter(function (element) {
            return element.attributeId;
        });
        paramArr = paramArr.filter(function (element, index) {
            return element.attributeTypeId && element.attributeId;
        });

        pushInput(specificationsArr);
        pushInput(salePointArr);
        pushInput(paramArr);

        var productAttribute = {
            specifications: specificationsArr,
            salePoints: salePointArr,
            params: paramArr
        }
        var checked = dtree.getCheckbarJsonArrParam("addCategory");
        // 商品属性信息
        var ext = {
            'categoryIds': checked.nodeId,
            'brandId': $("#brand").val(),
            'productAttribute': productAttribute
        }
        // 提交的对象
        var submitObj = {
            'basicProduct': basicProduct,
            'ext': ext
        };
        crud.post(pmsUrl + '/product', submitObj, function () {
            layer.close(parentIndex);
        })
    })

    /**
     * 将input的值转化为属性值
     */
    function pushInput(attributeArr) {
        for (var i = 0; i < attributeArr.length; i++) {
            var input = attributeArr[i].input;
            if (input) {
                var arr = input.split("|");
                for (var j = 0; j < arr.length; j++) {
                    if (arr[j]) {
                        attributeArr[i].attributeValues.push(arr[j]);
                    }
                }
            }
        }
    }

})
;
