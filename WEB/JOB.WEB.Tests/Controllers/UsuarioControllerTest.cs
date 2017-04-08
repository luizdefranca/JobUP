using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Controllers;
using JOB.WEB.Models;
using NUnit.Framework;
using System;
using System.Threading.Tasks;
using System.Web.Mvc;
using System.Linq;
using System.Data.Entity;
using JOB.DATA.ValueObject;

namespace JOB.WEB.Tests.Controllers
{
    [TestFixture]
    public class UsuarioControllerTest
    {
        private Contexto ctx = new Contexto();

        [SetUp]
        public void Initialize()
        {
            Mapper.Initialize(cfg =>
            {
                cfg.CreateMap<USUARIO, UsuarioViewModel>();
            });
        }

        [Test]
        public async Task Integration_ValidarInsertUsuario()
        {
            //gera uma nova classe para testes
            var domain = new USUARIO("USUARIO TESTE", new CPF("50869388720"), new RG(DATA.Enum.EnumUF.PE, "1234567"), DateTime.Now.AddYears(-30));
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            //se comunica com o controller
            var controller = new UsuarioController();
            ViewResult result = await controller.Create(model) as ViewResult;

            //recupera o novo usuario inserido no banco
            var domainNew = await ctx.Usuario.FirstAsync(w => w.CPF == new CPF("50869388720"));

            //teste finalmente se existe usuario inserido no banco e é o mesmo gerado no inicio do método
            Assert.Equals(model.CPF, domainNew.CPF);
        }
    }
}