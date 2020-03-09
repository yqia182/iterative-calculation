<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="get" action="/calculation/do/sample/all" target="_blank">
<#--                        <div class="form-group">-->
<#--                            <label>Sample Name</label>-->
<#--                            <input name="sampleName" type="text" class="form-control"}"/>-->
<#--                        </div>-->
                        <button type="submit" class="btn btn-primary btn-large">Run All Sample
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>