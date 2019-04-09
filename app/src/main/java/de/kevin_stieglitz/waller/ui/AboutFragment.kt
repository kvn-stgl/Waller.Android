package de.kevin_stieglitz.waller.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.kevin_stieglitz.waller.BuildConfig
import de.kevin_stieglitz.waller.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element


/**
 * A simple [Fragment] subclass.
 *
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val versionElement = Element()
        versionElement.title = getString(R.string.app_version, BuildConfig.VERSION_NAME)

        return AboutPage(context)
            .isRTL(false)
            .addItem(versionElement)
            .addGroup("Connect with us")
            .addEmail("kevin.stieglitz@jambit.com")
            .addWebsite("http://medyo.github.io/")
            .addPlayStore("com.ideashower.readitlater.pro")
            .addGitHub("medyo")
            .addInstagram("medyo80")
            .create()
    }


}
