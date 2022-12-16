//
//  File.swift
//  
//
//  Created by lease-emp-mac-suraj-sau on 2022/12/11.
//

import SwiftUI
import shared
import Utils

struct KanjiListScreen: View {

    @Environment(\.presentationMode) var presentationMode

    @ObservedObject var nativeVM = NativeViewModel(viewModel: KanjiListViewModel.init())

    let query: KanjiQuery

    var body: some View {
        ZStack {
//            if let state = self.dispatcher.state {
//                VStack {
//                    ScrollView {
//                        LazyVGrid(columns: [GridItem(.adaptive(minimum: 72), spacing: 24)], spacing: 16) {
//                            ForEach(state.items, id: \.self) { item in
//                                KanjiGradeItem(grade: item.value)
//                            }
//                        }
//                        .padding()
//                    }
//                    
//                    Spacer()
//                }
//            }
        }
        .onAppear {
            nativeVM.viewModel.doInitWith(query: query)
        }
    }
}
