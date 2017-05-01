﻿using System.Collections.Generic;
using System.Web.Optimization;

namespace JOB.WEB
{
    public class AsIsBundleOrderer : IBundleOrderer
    {
        public IEnumerable<BundleFile> OrderFiles(BundleContext context, IEnumerable<BundleFile> files)
        {
            return files;
        }
    }

    public class BundleConfig
    {
        // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.IgnoreList.Clear();

            RegisterAdministrador(bundles);
            RegisterHospital(bundles);
            RegisterMedico(bundles);
            RegisterPaciente(bundles);

            RegisterLayout(bundles);
            RegisterShared(bundles);
            RegisterAccount(bundles);

            BundleTable.EnableOptimizations = true;
        }

        private static void RegisterPaciente(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/Scripts/Paciente/dashboard/menu").Include(
                "~/Scripts/Paciente/dashboard-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Paciente/cirurgia/menu").Include(
                "~/Scripts/Paciente/cirurgia-menu.js"));
        }

        private static void RegisterMedico(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/Scripts/Medico/dashboard/menu").Include(
                "~/Scripts/Medico/dashboard-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Medico/agendamento/menu").Include(
                "~/Scripts/Medico/agendamento-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Medico/convenio/menu").Include(
               "~/Scripts/Medico/convenio-menu.js"));
        }

        private static void RegisterHospital(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/Scripts/Hospital/dashboard/menu").Include(
                "~/Scripts/Hospital/dashboard-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Hospital/agendamento/menu").Include(
                "~/Scripts/Hospital/agendamento-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Hospital/medico/menu").Include(
                "~/Scripts/Hospital/medico-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Hospital/bloco/menu").Include(
                "~/Scripts/Hospital/bloco-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Hospital/convenio/menu").Include(
                "~/Scripts/Hospital/convenio-menu.js"));
        }

        private static void RegisterAdministrador(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/Scripts/Administrador/dashboard/menu").Include(
                "~/Scripts/Administrador/dashboard-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/cid/menu").Include(
                "~/Scripts/Administrador/cid-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/convenio/menu").Include(
                "~/Scripts/Administrador/convenio-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/agendamento/menu").Include(
                "~/Scripts/Administrador/agendamento-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/hospital/menu").Include(
                "~/Scripts/Administrador/hospital-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/medico/menu").Include(
                "~/Scripts/Administrador/medico-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/opme/menu").Include(
                "~/Scripts/Administrador/opme-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/paciente/menu").Include(
                "~/Scripts/Administrador/paciente-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/procedimento/menu").Include(
                "~/Scripts/Administrador/procedimento-menu.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Administrador/unidade/menu").Include(
                "~/Scripts/Administrador/unidade-menu.js"));
        }

        private static void RegisterAccount(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/Scripts/Account/Login").Include(
                "~/Scripts/Account/Login.js"));

            bundles.Add(new ScriptBundle("~/Scripts/Account/Register").Include(
                "~/Scripts/Account/Register.js"));
        }

        private static void RegisterShared(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/Scripts/Shared/_Layout").Include(
                "~/Scripts/Shared/_Layout.js"));
        }

        private static void RegisterLayout(BundleCollection bundles)
        {
            // bootstrap
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/bootstrap/js").Include(
                "~/AdminLTE/bootstrap/js/bootstrap.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/bootstrap/css").Include(
                "~/AdminLTE/bootstrap/css/bootstrap.min.css", new CssRewriteUrlTransform()));

            // dist
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/dist/js").Include(
                "~/AdminLTE/dist/js/app.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/dist/css").Include(
                "~/AdminLTE/dist/css/admin-lte.min.css"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/dist/css/skins").Include(
                "~/AdminLTE/dist/css/skins/_all-skins.min.css"));

            // documentation
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/documentation/js").Include(
                "~/AdminLTE/documentation/js/docs.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/documentation/css").Include(
                "~/AdminLTE/documentation/css/style.css"));

            // plugins | bootstrap-slider
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/bootstrap-slider/js").Include(
                                        "~/AdminLTE/plugins/bootstrap-slider/js/bootstrap-slider.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/bootstrap-slider/css").Include(
                                        "~/AdminLTE/plugins/bootstrap-slider/css/slider.css"));

            // plugins | bootstrap-wysihtml5
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/bootstrap-wysihtml5/js").Include(
                                         "~/AdminLTE/plugins/bootstrap-wysihtml5/js/bootstrap3-wysihtml5.all.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/bootstrap-wysihtml5/css").Include(
                                        "~/AdminLTE/plugins/bootstrap-wysihtml5/css/bootstrap3-wysihtml5.min.css"));

            // plugins | chartjs
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/chartjs/js").Include(
                                         "~/AdminLTE/plugins/chartjs/js/chart.min.js"));

            // plugins | ckeditor
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/ckeditor/js").Include(
                                         "~/AdminLTE/plugins/ckeditor/js/ckeditor.js"));

            // plugins | colorpicker
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/colorpicker/js").Include(
                                         "~/AdminLTE/plugins/colorpicker/js/bootstrap-colorpicker.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/colorpicker/css").Include(
                                        "~/AdminLTE/plugins/colorpicker/css/bootstrap-colorpicker.css"));

            // plugins | datatables
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/datatables/js").Include(
                                        "~/AdminLTE/plugins/datatables/js/jquery.dataTables.js",
                                        "~/AdminLTE/plugins/datatables/js/dataTables.bootstrap.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/datatables/css").Include(
                                        "~/AdminLTE/plugins/datatables/css/dataTables.bootstrap.css"));

            // plugins | datepicker
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/datepicker/js").Include(
                                         "~/AdminLTE/plugins/datepicker/js/bootstrap-datepicker.js",
                                         "~/AdminLTE/plugins/datepicker/js/locales/bootstrap-datepicker*"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/datepicker/css").Include(
                                        "~/AdminLTE/plugins/datepicker/css/datepicker3.css"));

            // plugins | daterangepicker
            var bundleDateRunger = new ScriptBundle("~/Bundle/AdminLTE/plugins/daterangepicker/js") { Orderer = new AsIsBundleOrderer() };

            bundleDateRunger
                .Include("~/AdminLTE/plugins/daterangepicker/js/moment.min.js")
                .Include("~/AdminLTE/plugins/daterangepicker/js/daterangepicker.js");
            bundles.Add(bundleDateRunger);

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/daterangepicker/css").Include(
                                        "~/AdminLTE/plugins/daterangepicker/css/daterangepicker.css"));

            // plugins | fastclick
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/fastclick/js").Include(
                                         "~/AdminLTE/plugins/fastclick/js/fastclick.min.js"));

            // plugins | flot
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/flot/js").Include(
                                         "~/AdminLTE/plugins/flot/js/jquery.flot.min.js",
                                         "~/AdminLTE/plugins/flot/js/jquery.flot.resize.min.js",
                                         "~/AdminLTE/plugins/flot/js/jquery.flot.pie.min.js",
                                         "~/AdminLTE/plugins/flot/js/jquery.flot.categories.min.js"));

            // plugins | font-awesome
            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/font-awesome/css").Include(
                                        "~/AdminLTE/plugins/font-awesome/css/font-awesome.min.css", new CssRewriteUrlTransform()));

            // plugins | fullcalendar
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/fullcalendar/js").Include(
                                         "~/AdminLTE/plugins/fullcalendar/js/fullcalendar.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/fullcalendar/css").Include(
                                        "~/AdminLTE/plugins/fullcalendar/css/fullcalendar.min.css"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/fullcalendar/css/print").Include(
                                        "~/AdminLTE/plugins/fullcalendar/css/print/fullcalendar.print.css"));

            // plugins | icheck
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/icheck/js").Include(
                                         "~/AdminLTE/plugins/icheck/js/icheck.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/icheck/css").Include(
                                        "~/AdminLTE/plugins/icheck/css/square/blue.css", new CssRewriteUrlTransform()));

            // plugins | input-mask
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/input-mask/js").Include(
                                         "~/AdminLTE/plugins/input-mask/js/jquery.inputmask.js",
                                         "~/AdminLTE/plugins/input-mask/js/jquery.inputmask.date.extensions.js",
                                         "~/AdminLTE/plugins/input-mask/js/jquery.inputmask.extensions.js"));

            // plugins | ionicons
            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/ionicons/css").Include(
                                        "~/AdminLTE/plugins/ionicons/css/ionicons.min.css"));

            // plugins | ionslider
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/ionslider/js").Include(
                                         "~/AdminLTE/plugins/ionslider/js/ion.rangeSlider.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/ionslider/css").Include(
                                        "~/AdminLTE/plugins/ionslider/css/ion.rangeSlider.css",
                                        "~/AdminLTE/plugins/ionslider/css/ion.rangeSlider.skinNice.css"));

            // plugins | jquery
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/jquery/js").Include(
                                         "~/AdminLTE/plugins/jquery/js/jquery-3.2.1.min.js"));

            // plugins | jquery-validate
            var bundleValidate = new ScriptBundle("~/Bundle/AdminLTE/plugins/jquery-validate/js") { Orderer = new AsIsBundleOrderer() };

            bundleValidate
                .Include("~/AdminLTE/plugins/jquery-validate/js/jquery.validate.min.js")
                .Include("~/AdminLTE/plugins/jquery-validate/js/jquery.validate.unobtrusive.min.js")
                .Include("~/Scripts/globalize.js")
                .Include("~/Scripts/jquery.validate.globalize.min.js");
            bundles.Add(bundleValidate);

            // plugins | jquery-ui
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/jquery-ui/js").Include(
                                         "~/AdminLTE/plugins/jquery-ui/js/jquery-ui.min.js"));

            // plugins | jvectormap
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/jvectormap/js").Include(
                                         "~/AdminLTE/plugins/jvectormap/js/jquery-jvectormap-1.2.2.min.js",
                                         "~/AdminLTE/plugins/jvectormap/js/jquery-jvectormap-world-mill-en.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/jvectormap/css").Include(
                                        "~/AdminLTE/plugins/jvectormap/css/jquery-jvectormap-1.2.2.css"));

            // plugins | knob
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/knob/js").Include(
                                         "~/AdminLTE/plugins/knob/js/jquery.knob.js"));

            // plugins | morris
            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/morris/css").Include(
                                        "~/AdminLTE/plugins/morris/css/morris.css"));

            // plugins | momentjs
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/momentjs/js").Include(
                                         "~/AdminLTE/plugins/momentjs/js/moment.min.js"));

            // plugins | pace
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/pace/js").Include(
                                         "~/AdminLTE/plugins/pace/js/pace.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/pace/css").Include(
                                        "~/AdminLTE/plugins/pace/css/pace.min.css"));

            // plugins | slimscroll
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/slimscroll/js").Include(
                                         "~/AdminLTE/plugins/slimscroll/js/jquery.slimscroll.min.js"));

            // plugins | sparkline
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/sparkline/js").Include(
                                         "~/AdminLTE/plugins/sparkline/js/jquery.sparkline.min.js"));

            // plugins | timepicker
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/timepicker/js").Include(
                                         "~/AdminLTE/plugins/timepicker/js/bootstrap-timepicker.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/timepicker/css").Include(
                                        "~/AdminLTE/plugins/timepicker/css/bootstrap-timepicker.min.css"));

            // plugins | raphael
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/raphael/js").Include(
                                         "~/AdminLTE/plugins/raphael/js/raphael-min.js"));

            // plugins | select2
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/select2/js").Include(
                                         "~/AdminLTE/plugins/select2/js/select2.full.min.js"));

            bundles.Add(new StyleBundle("~/Bundle/AdminLTE/plugins/select2/css").Include(
                                        "~/AdminLTE/plugins/select2/css/select2.min.css"));

            // plugins | morris
            bundles.Add(new ScriptBundle("~/Bundle/AdminLTE/plugins/morris/js").Include(
                                         "~/AdminLTE/plugins/morris/js/morris.min.js"));
        }
    }
}