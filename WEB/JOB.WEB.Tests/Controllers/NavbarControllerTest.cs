using JOB.WEB.Models;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using System.Web.Mvc;

namespace JOB.WEB.Tests.Controllers
{
    /// <summary>
    /// Summary description for NavbarControllerTest
    /// </summary>
    [TestClass]
    public class NavbarControllerTest
    {
        [TestMethod]
        public void Navbar_Items_Return_NotNull()
        {
            var _controller = new NavbarController();

            var result = _controller.Index();
            var rresult = (PartialViewResult)result;

            Assert.IsNotNull(result);
            Assert.IsInstanceOfType(result, typeof(PartialViewResult));
            Assert.AreEqual(rresult.ViewName, "_Navbar");
            Assert.IsInstanceOfType(rresult.Model, typeof(IEnumerable<Navbar>));
        }
    }
}