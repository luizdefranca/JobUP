using AutoMapper;
using JOB.DATA.Domain;
using JOB.DATA.ValueObject;
using JOB.WEB.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web.Mvc;
using JOB.WEB.Validation;
using System.Net;
using System.IO;
using JOB.DATA;

namespace JOB.WEB.Controllers
{
    public class UsuarioController : Controller
    {
        private const string _apiUrl = "http://jobapi.azurewebsites.net/api/usuario/";

        // GET: Usuario
        public async Task<ActionResult> Index()
        {
            using (HttpClient client = new HttpClient())
            {
                client.BaseAddress = new Uri(_apiUrl);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage response = await client.GetAsync(_apiUrl);
                if (response.IsSuccessStatusCode)
                {
                    var data = await response.Content.ReadAsStringAsync();
                    var domain = JsonConvert.DeserializeObject<List<USUARIO>>(data); //quebra o json para a classe original
                    var model = Mapper.Map<List<UsuarioViewModel>>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)
                    return View(model);
                }

                return View();
            }
        }

        // GET: Usuario/Details/5
        public async Task<ActionResult> Details(int id)
        {
            using (HttpClient client = new HttpClient())
            {
                client.BaseAddress = new Uri(_apiUrl + id);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage response = await client.GetAsync(_apiUrl + id);
                if (response.IsSuccessStatusCode)
                {
                    var data = await response.Content.ReadAsStringAsync();
                    var domain = JsonConvert.DeserializeObject<USUARIO>(data); //quebra o json para a classe original
                    var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)
                    return View(model);
                }

                return View();
            }
        }

        // GET: Usuario/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Usuario/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(UsuarioViewModel obj)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                var newobj = new USUARIO(obj.NOME, new CPF(obj.CPF), new RG(obj.RgUF, obj.RgNR), obj.DT_NASCIMENTO);

                Contexto ctx = new Contexto();

                ctx.Usuario.Add(newobj);
                ctx.SaveChanges();

                //var httpWebRequest = (HttpWebRequest)WebRequest.Create(_apiUrl);
                //httpWebRequest.ContentType = "application/json";
                //httpWebRequest.Method = "POST";

                //using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                //{
                //    var domain = JsonConvert.SerializeObject(newobj);

                //    streamWriter.Write(domain);
                //    streamWriter.Flush();
                //    streamWriter.Close();
                //}

                //var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                //using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                //{
                //    var result = streamReader.ReadToEnd();
                //}

                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        // GET: Usuario/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Usuario/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Usuario/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Usuario/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}